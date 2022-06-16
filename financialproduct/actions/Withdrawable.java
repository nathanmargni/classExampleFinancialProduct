package ch.supsi.dti.miniproject.financialproduct.actions;

import ch.supsi.dti.miniproject.exception.FundsNotSufficientException;
import ch.supsi.dti.miniproject.exception.InvalidAmountException;

public interface Withdrawable {
    boolean withdraw(double amount) throws FundsNotSufficientException, InvalidAmountException;
}
