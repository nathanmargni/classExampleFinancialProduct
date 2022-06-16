package ch.supsi.dti.miniproject.exception;

import ch.supsi.dti.miniproject.financialproduct.FinancialProduct;

public class SupervisorInvalidException extends SupervisorException {
    public SupervisorInvalidException(FinancialProduct product) {
        super(product, "The supervisor " + product.getCustomer() + " owns the product. Cannot be set as a supervisor");
    }
}
