package ch.supsi.dti.miniproject.financialproduct.card;

import ch.supsi.dti.miniproject.exception.*;
import ch.supsi.dti.miniproject.financialproduct.enums.Circuit;
import ch.supsi.dti.miniproject.person.Banker;
import ch.supsi.dti.miniproject.person.Person;

import java.time.LocalDate;

public class CreditCard extends Card {

    private static final double WITHDRAWAL_FEE_PERCENT = 0.04;
    private static final double WITHDRAWAL_FEE_MINIMUM = 10.0;

    // The maximum amount of money that you can spend in a month with the card
    private final double cardLimit;
    // The monthly interest rate of the card
    private final double interestRate;
    // Is a revolving card? (revolving: you are not obliged to pay in full your monthly bill)
    private final boolean revolving;
    // The current available credit
    private double currentAvailability;

    public CreditCard(LocalDate openingDate, Banker banker, Person customer, double cardLimit, double interestRate, boolean revolving, Circuit circuit, String number, int expirationMonth, int expirationYear, int CVV2) {
        super(openingDate, banker, customer, circuit, number, expirationMonth, expirationYear, CVV2);
        this.cardLimit = cardLimit;
        this.interestRate = interestRate;
        this.currentAvailability = cardLimit;
        this.revolving = revolving;
    }

    public boolean registerTransaction(double amount) throws FundsNotSufficientException {
        if (this.currentAvailability < amount)
            throw new FundsNotSufficientException(this, amount, this.currentAvailability);
        this.currentAvailability -= amount;
        return true;
    }

    public double getCurrentSpending() {
        // How much money the customer has spent?
        return this.cardLimit - this.currentAvailability;
    }

    public void payBill(double amount) throws InvalidAmountException, PaymentRefusedException {
        if (amount <= 0)
            throw new InvalidAmountException(this, amount);
        // is paying in full?
        // It is also possible that the customer pays more than the bill, we have to keep that credit!
        if (amount >= getCurrentSpending()) {
            this.currentAvailability += amount;
        } else if (this.revolving) {
            // Customer must pay interests on the difference between the bill and the payment
            double interest = (getCurrentSpending() - amount) * this.interestRate;
            this.currentAvailability = this.currentAvailability + amount - interest;
        }
        throw new PaymentRefusedException(this);
    }

    public double getCardLimit() {
        return cardLimit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double getCurrentAvailability() {
        return currentAvailability;
    }

    public boolean isRevolving() {
        return revolving;
    }


    @Override
    public boolean withdrawWitPin(double amount, int pin) throws FundsNotSufficientException, InvalidAmountException, PinNotValidException, CardBlockedException {
        this.checkPin(pin);
        double potentialCommission = amount * WITHDRAWAL_FEE_PERCENT;
        potentialCommission = potentialCommission < WITHDRAWAL_FEE_MINIMUM ? WITHDRAWAL_FEE_MINIMUM : potentialCommission;

        if ((this.currentAvailability - amount - potentialCommission) < 0)
            throw new FundsNotSufficientException(this, amount, this.currentAvailability, potentialCommission);

        this.currentAvailability -= (amount + potentialCommission);
        return true;
    }
}
