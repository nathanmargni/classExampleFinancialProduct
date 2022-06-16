package ch.supsi.dti.miniproject.exception;

import ch.supsi.dti.miniproject.financialproduct.FinancialProduct;

public class InvalidAmountException extends MoneyException {
    private final double invalidAmount;

    public InvalidAmountException(FinancialProduct product, double invalidAmount) {
        super(product, "The amount " + invalidAmount + " CHF is not valid");
        this.invalidAmount = invalidAmount;
    }

    public double getInvalidAmount() {
        return invalidAmount;
    }
}
