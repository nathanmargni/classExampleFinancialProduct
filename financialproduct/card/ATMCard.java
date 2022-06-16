package ch.supsi.dti.miniproject.financialproduct.card;

import ch.supsi.dti.miniproject.exception.CardBlockedException;
import ch.supsi.dti.miniproject.exception.FundsNotSufficientException;
import ch.supsi.dti.miniproject.exception.InvalidAmountException;
import ch.supsi.dti.miniproject.exception.PinNotValidException;
import ch.supsi.dti.miniproject.financialproduct.account.CurrentAccount;
import ch.supsi.dti.miniproject.financialproduct.actions.WithdrawableWithCard;
import ch.supsi.dti.miniproject.financialproduct.enums.Circuit;
import ch.supsi.dti.miniproject.person.Banker;
import ch.supsi.dti.miniproject.person.Person;

import java.time.LocalDate;

public class ATMCard extends Card {

    private final WithdrawableWithCard account;

    public ATMCard(LocalDate openingDate, Banker banker, Person customer, WithdrawableWithCard account, Circuit circuit, String number, int expirationMonth, int expirationYear, int CVV2) {
        super(openingDate, banker, customer, circuit, number, expirationMonth, expirationYear, CVV2);
        this.account = account;
    }


    @Override
    public boolean withdrawWitPin(double amount, int pin) throws FundsNotSufficientException, InvalidAmountException, PinNotValidException, CardBlockedException {
        this.checkPin(pin);
        return this.account.withdrawWithCard(amount);
    }

    public boolean isCurrentAccount() {
        return this.account instanceof CurrentAccount;
    }
}
