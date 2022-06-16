package ch.supsi.dti.miniproject.exception;

import ch.supsi.dti.miniproject.financialproduct.FinancialProduct;

public class NumberOfCreditCardsLimitReachedException extends TechnicalException {
    public NumberOfCreditCardsLimitReachedException(FinancialProduct product) {
        super(product, "The maximum of 6 credit cards is already reached");
    }
}
