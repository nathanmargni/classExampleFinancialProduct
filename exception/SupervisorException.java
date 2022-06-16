package ch.supsi.dti.miniproject.exception;

import ch.supsi.dti.miniproject.financialproduct.FinancialProduct;

public abstract class SupervisorException extends FinancialProductException {

    public SupervisorException(FinancialProduct product, String message) {
        super(product, message);
    }

    public SupervisorException(FinancialProduct product, Throwable e) {
        super(product, e);
    }
}
