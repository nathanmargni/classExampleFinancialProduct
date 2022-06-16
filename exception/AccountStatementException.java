package ch.supsi.dti.miniproject.exception;

import ch.supsi.dti.miniproject.financialproduct.FinancialProduct;

public class AccountStatementException extends TechnicalException {
    public AccountStatementException(FinancialProduct product, Throwable e) {
        super(product, e);
    }
}
