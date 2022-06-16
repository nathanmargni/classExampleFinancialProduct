package ch.supsi.dti.miniproject.exception;

import ch.supsi.dti.miniproject.financialproduct.FinancialProduct;

public class PinNotValidException extends TechnicalException {

    private final int attempts;

    public PinNotValidException(FinancialProduct product, int attempts) {
        super(product, "Pin is not valid, this was the " + attempts + " attempt");
        this.attempts = attempts;
    }

    public int getAttempts() {
        return attempts;
    }
}
