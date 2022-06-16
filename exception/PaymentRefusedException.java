package ch.supsi.dti.miniproject.exception;

import ch.supsi.dti.miniproject.financialproduct.FinancialProduct;

public class PaymentRefusedException extends MoneyException {

    public PaymentRefusedException(FinancialProduct product) {
        super(product, "Payment was refused");
    }
}
