package ch.supsi.dti.miniproject.exception;

import ch.supsi.dti.miniproject.financialproduct.FinancialProduct;

public class FundsNotSufficientException extends MoneyException {
    private final double requestedAmount;
    private final double availableFunds;
    private double requiredFee;

    public FundsNotSufficientException(FinancialProduct product, double requestedAmount, double availableFunds) {
        super(product, "Is not possible to provide the requested amount of " + requestedAmount + " CHF because available funds are " + availableFunds);
        this.requestedAmount = requestedAmount;
        this.availableFunds = availableFunds;
    }

    public FundsNotSufficientException(FinancialProduct product, double requestedAmount, double availableFunds, double requiredFee) {
        super(product, "There are no enough funds to provide the requested amount (" + requestedAmount + "), fee is " + requiredFee + " and available fund is " + availableFunds);
        this.requestedAmount = requestedAmount;
        this.availableFunds = availableFunds;
        this.requiredFee = requiredFee;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public double getAvailableFunds() {
        return availableFunds;
    }

    public double getRequiredFee() {
        return requiredFee;
    }
}
