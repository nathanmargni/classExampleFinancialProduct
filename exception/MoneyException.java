package ch.supsi.dti.miniproject.exception;

import ch.supsi.dti.miniproject.financialproduct.FinancialProduct;

public abstract class MoneyException extends FinancialProductException {
    public MoneyException(FinancialProduct product, String message) {
        super(product, message);
    }

    public MoneyException(FinancialProduct product, Throwable e) {
        super(product, e);
    }
}
