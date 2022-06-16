package ch.supsi.dti.miniproject.financialproduct.account;

import ch.supsi.dti.miniproject.exception.FundsNotSufficientException;
import ch.supsi.dti.miniproject.exception.InvalidAmountException;
import ch.supsi.dti.miniproject.exception.SupervisorAlreadySetException;
import ch.supsi.dti.miniproject.exception.SupervisorInvalidException;
import ch.supsi.dti.miniproject.financialproduct.actions.Withdrawable;
import ch.supsi.dti.miniproject.financialproduct.fiscalsurveillace.Surveillable;
import ch.supsi.dti.miniproject.financialproduct.fiscalsurveillace.SurveillanceDelegate;
import ch.supsi.dti.miniproject.person.Banker;
import ch.supsi.dti.miniproject.person.Person;
import ch.supsi.dti.miniproject.person.SurveillanceSupervisor;

import java.time.LocalDate;

public class SavingsAccount extends Account implements Surveillable, Withdrawable {

    private static final int FREE_WITHDRAWALS = 8;
    private static final double WITHDRAWAL_FEE = 10.0;
    private final double interestRate;
    private final SurveillanceDelegate surveillanceDelegate;
    private int withdrawalsCount = 0;
    private LocalDate lastWithdrawalsCountReset;

    public SavingsAccount(LocalDate openingDate, Banker banker, Person customer, double interestRate, double balance) {
        super(openingDate, banker, customer, balance);
        this.lastWithdrawalsCountReset = openingDate;
        this.interestRate = interestRate;
        this.surveillanceDelegate = new SurveillanceDelegate(this);
    }

    private void checkFreeWithdrawals() {
        // If a whole year (or more) is passed since the last reset, do it
        if (lastWithdrawalsCountReset.plusYears(1).isBefore(LocalDate.now())) {
            this.withdrawalsCount = 0;
            this.lastWithdrawalsCountReset = this.lastWithdrawalsCountReset.withYear(LocalDate.now().getYear());
        }
    }

    @Override
    public boolean withdraw(double amount) throws FundsNotSufficientException, InvalidAmountException {
        if (amount <= 0)
            throw new InvalidAmountException(this, amount);

        this.checkFreeWithdrawals();
        double fee = this.withdrawalsCount < FREE_WITHDRAWALS ? 0.0 : WITHDRAWAL_FEE;

        if ((amount + fee) > this.getBalance())
            throw new FundsNotSufficientException(this, amount, this.getBalance(), fee);

        this.setBalance(this.getBalance() - (amount + fee));
        this.withdrawalsCount++;

        return true;
    }

    @Override
    public boolean deposit(double amount) throws InvalidAmountException {
        if (amount > 0) {
            this.setBalance(this.getBalance() + amount);
            return true;
        } else throw new InvalidAmountException(this, amount);

    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public int getWithdrawalsCount() {
        return this.withdrawalsCount;
    }

    @Override
    public SurveillanceSupervisor getSupervisor() {
        return this.surveillanceDelegate.getSupervisor();
    }

    @Override
    public void setSupervisor(SurveillanceSupervisor supervisor) throws SupervisorAlreadySetException, SupervisorInvalidException {
        this.surveillanceDelegate.setSupervisor(supervisor);
    }

    @Override
    public boolean isUnderSurveillance() {
        return this.surveillanceDelegate.isUnderSurveillance();
    }

    @Override
    public void setSurveillanceEnabled(boolean what) {
        this.surveillanceDelegate.setSurveillanceEnabled(what);
    }

    @Override
    public boolean withdrawWithCard(double amount) throws FundsNotSufficientException, InvalidAmountException {
        return this.withdraw(amount);
    }
}
