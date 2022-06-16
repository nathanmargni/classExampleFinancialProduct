package ch.supsi.dti.miniproject.exception;

import ch.supsi.dti.miniproject.financialproduct.FinancialProduct;

public class NumberOfCardsLimitReachedException  extends TechnicalException {

    public NumberOfCardsLimitReachedException(FinancialProduct product, int limit) {
        super(product, "Limit of "+limit+" cards reached");
    }

}
