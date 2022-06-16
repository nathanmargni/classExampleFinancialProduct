package ch.supsi.dti.miniproject.exception;

import ch.supsi.dti.miniproject.financialproduct.FinancialProduct;

public class CardBlockedException extends TechnicalException {
    public CardBlockedException(FinancialProduct product) {
        super(product, "Card is blocked");
    }
}
