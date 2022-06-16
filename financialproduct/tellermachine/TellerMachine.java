package ch.supsi.dti.miniproject.financialproduct.tellermachine;

import ch.supsi.dti.miniproject.exception.CardBlockedException;
import ch.supsi.dti.miniproject.exception.FundsNotSufficientException;
import ch.supsi.dti.miniproject.exception.InvalidAmountException;
import ch.supsi.dti.miniproject.exception.PinNotValidException;
import ch.supsi.dti.miniproject.financialproduct.actions.WithdrawableWithPin;

public class TellerMachine {

    public static boolean withdraw(WithdrawableWithPin card, double amount, int pin) throws InvalidAmountException, PinNotValidException, FundsNotSufficientException, CardBlockedException {
        return card.withdrawWitPin(amount, pin);
    }

}
