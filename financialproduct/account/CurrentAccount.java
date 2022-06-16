package ch.supsi.dti.miniproject.financialproduct.account;

import ch.supsi.dti.miniproject.exception.FundsNotSufficientException;
import ch.supsi.dti.miniproject.exception.InvalidAmountException;
import ch.supsi.dti.miniproject.person.Banker;
import ch.supsi.dti.miniproject.person.Person;

import java.time.LocalDate;

public class CurrentAccount extends Account {

    private static final double WITHDRAWAL_FEE = 2.0;

    private final double overdraft;

    public CurrentAccount(LocalDate openingDate, Banker banker, Person customer, double overdraft, double balance) {
        super(openingDate, banker, customer, balance);
        this.overdraft = overdraft;
    }

    @Override
    public boolean withdraw(double amount) throws FundsNotSufficientException, InvalidAmountException {
        if (amount <= 0)
            throw new InvalidAmountException(this, amount);

        if (this.getBalance() < (amount + WITHDRAWAL_FEE))
            throw new FundsNotSufficientException(this, amount, this.getBalance());

        this.setBalance(this.getBalance() - (amount + WITHDRAWAL_FEE));
        return true;
    }

    public boolean directDebit(double amount) throws FundsNotSufficientException, InvalidAmountException {
        if (amount <= 0)
            throw new InvalidAmountException(this, amount);

        if (this.getBalance() < 0 && (this.overdraft - Math.abs(this.getBalance())) < amount)
            throw new FundsNotSufficientException(this, amount, this.getBalance());

        this.setBalance(this.getBalance() - amount);
        return true;
    }

    @Override
    public boolean deposit(double amount) throws InvalidAmountException {
        if (amount > 0) {
            this.setBalance(this.getBalance() + amount);
            return true;
        } else throw new InvalidAmountException(this, amount);

    }

    public double getOverdraft() {
        return overdraft;
    }

    @Override
    public boolean withdrawWithCard(double amount) throws FundsNotSufficientException, InvalidAmountException {
        return this.directDebit(amount);
    }
}
