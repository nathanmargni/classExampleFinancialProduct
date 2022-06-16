package ch.supsi.dti.miniproject.financialproduct.insurance;

import ch.supsi.dti.miniproject.exception.InvalidAmountException;
import ch.supsi.dti.miniproject.exception.SupervisorAlreadySetException;
import ch.supsi.dti.miniproject.exception.SupervisorInvalidException;
import ch.supsi.dti.miniproject.financialproduct.actions.Balanceable;
import ch.supsi.dti.miniproject.financialproduct.actions.Depositable;
import ch.supsi.dti.miniproject.financialproduct.fiscalsurveillace.Surveillable;
import ch.supsi.dti.miniproject.financialproduct.fiscalsurveillace.SurveillanceDelegate;
import ch.supsi.dti.miniproject.person.Banker;
import ch.supsi.dti.miniproject.person.Person;
import ch.supsi.dti.miniproject.person.SurveillanceSupervisor;

import java.time.LocalDate;

public class LifeInsurance extends Insurance implements Depositable, Surveillable, Balanceable {

    private final SurveillanceDelegate surveillanceDelegate;
    private double balance = 0.0;

    public LifeInsurance(LocalDate openingDate, Banker banker, Person customer, double annualFee) {
        super(openingDate, banker, customer, annualFee);
        this.surveillanceDelegate = new SurveillanceDelegate(this);
    }

    @Override
    public boolean deposit(double amount) throws InvalidAmountException {
        if (amount <= 0)
            throw new InvalidAmountException(this, amount);
        this.balance += amount;
        return true;
    }

    @Override
    public SurveillanceSupervisor getSupervisor() {
        return this.surveillanceDelegate.getSupervisor();
    }

    @Override
    public void setSupervisor(SurveillanceSupervisor supervisor) throws SupervisorInvalidException, SupervisorAlreadySetException {
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
    public double getBalance() {
        return this.balance;
    }
}
