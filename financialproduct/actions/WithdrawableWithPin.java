package ch.supsi.dti.miniproject.financialproduct.actions;

import ch.supsi.dti.miniproject.exception.CardBlockedException;
import ch.supsi.dti.miniproject.exception.FundsNotSufficientException;
import ch.supsi.dti.miniproject.exception.InvalidAmountException;
import ch.supsi.dti.miniproject.exception.PinNotValidException;

public interface WithdrawableWithPin {
    boolean withdrawWitPin(double amount, int pin) throws FundsNotSufficientException, InvalidAmountException, PinNotValidException, CardBlockedException;

}
