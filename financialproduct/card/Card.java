package ch.supsi.dti.miniproject.financialproduct.card;

import ch.supsi.dti.miniproject.exception.CardBlockedException;
import ch.supsi.dti.miniproject.exception.PinNotValidException;
import ch.supsi.dti.miniproject.financialproduct.FinancialProduct;
import ch.supsi.dti.miniproject.financialproduct.actions.WithdrawableWithPin;
import ch.supsi.dti.miniproject.financialproduct.enums.Circuit;
import ch.supsi.dti.miniproject.person.Banker;
import ch.supsi.dti.miniproject.person.Person;

import java.time.LocalDate;
import java.util.Random;

public abstract class Card extends FinancialProduct implements WithdrawableWithPin {

    private final Circuit circuit;
    private final String number;
    private final int expirationMonth;
    private final int expirationYear;
    private final int CVV2;
    private int pin = 0000;

    private int attempts = 0;

    public Card(LocalDate openingDate, Banker banker, Person customer, Circuit circuit, String number, int expirationMonth, int expirationYear, int CVV2) {
        super(openingDate, banker, customer);
        this.circuit = circuit;
        this.number = number;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.CVV2 = CVV2;
    }

    public int editPIN(int oldPin) {
        if (oldPin == this.pin) {
            this.pin = new Random().nextInt(99999);
            return this.pin;
        }
        return -1;
    }

    protected void checkPin(int pin) throws PinNotValidException, CardBlockedException {
        if (this.attempts == 3)
            throw new CardBlockedException(this);
        if (this.pin != pin) {
            this.attempts++;
            throw new PinNotValidException(this, this.attempts);
        }
        this.attempts = 0;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public String getNumber() {
        return number;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public int getCVV2() {
        return CVV2;
    }
}
