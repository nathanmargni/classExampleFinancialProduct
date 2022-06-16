package ch.supsi.dti.miniproject.financialproduct.actions;

import ch.supsi.dti.miniproject.exception.FundsNotSufficientException;
import ch.supsi.dti.miniproject.exception.InvalidAmountException;

public interface WithdrawableWithCard {

    boolean withdrawWithCard(double amount) throws FundsNotSufficientException, InvalidAmountException;
}
