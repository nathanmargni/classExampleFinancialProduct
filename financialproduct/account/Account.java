package ch.supsi.dti.miniproject.financialproduct.account;

import ch.supsi.dti.miniproject.accountstatement.AccountStatement;
import ch.supsi.dti.miniproject.accountstatement.AccountStatementDelegate;
import ch.supsi.dti.miniproject.exception.AccountStatementException;
import ch.supsi.dti.miniproject.financialproduct.FinancialProduct;
import ch.supsi.dti.miniproject.financialproduct.actions.Balanceable;
import ch.supsi.dti.miniproject.financialproduct.actions.Depositable;
import ch.supsi.dti.miniproject.financialproduct.actions.Withdrawable;
import ch.supsi.dti.miniproject.financialproduct.actions.WithdrawableWithCard;
import ch.supsi.dti.miniproject.person.Banker;
import ch.supsi.dti.miniproject.person.Person;

import java.time.LocalDate;
import java.util.Random;

public abstract class Account extends FinancialProduct implements Withdrawable, WithdrawableWithCard, Depositable, Balanceable, AccountStatement {

    private final int accountNumber;
    private final AccountStatementDelegate accountStatementDelegate;
    private double balance;

    public Account(LocalDate openingDate, Banker banker, Person customer, double initialBalance) {
        super(openingDate, banker, customer);
        this.balance = initialBalance;
        this.accountStatementDelegate = new AccountStatementDelegate(this);
        this.accountNumber = new Random().nextInt(9999999);
    }

    public double getBalance() {
        return this.balance;
    }

    protected void setBalance(double amount) {
        this.balance = amount;
    }

    @Override
    public void generateStatement() throws AccountStatementException {
        this.accountStatementDelegate.generateStatement();
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
