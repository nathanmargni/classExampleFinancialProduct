package ch.supsi.dti.miniproject.exception;

import ch.supsi.dti.miniproject.financialproduct.FinancialProduct;

public abstract class TechnicalException extends FinancialProductException{
    public TechnicalException(FinancialProduct product, String message) {
        super(product, message);
    }

    public TechnicalException(FinancialProduct product, Throwable e) {
        super(product, e);
    }
}
