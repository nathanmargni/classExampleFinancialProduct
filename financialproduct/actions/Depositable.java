package ch.supsi.dti.miniproject.financialproduct.actions;

import ch.supsi.dti.miniproject.exception.InvalidAmountException;

public interface Depositable {

    boolean deposit(double amount) throws InvalidAmountException;
}
