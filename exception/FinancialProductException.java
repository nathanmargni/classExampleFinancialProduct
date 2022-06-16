package ch.supsi.dti.miniproject.exception;

import ch.supsi.dti.miniproject.financialproduct.FinancialProduct;

public abstract class FinancialProductException extends Exception {
    private FinancialProduct product;

    public FinancialProductException(FinancialProduct product, String message) {
        super("In product " + product.getClass().getSimpleName() + " of customer "+product.getCustomer()+": " + message);
        this.product = product;
    }

    public FinancialProductException(FinancialProduct product, Throwable e) {
        super("In product " + product.getClass().getSimpleName() + " of customer "+product.getCustomer()+": " + e.getMessage(), e);
        this.product = product;
    }

    public FinancialProduct getProduct() {
        return product;
    }
}
