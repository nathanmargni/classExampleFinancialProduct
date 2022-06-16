package ch.supsi.dti.miniproject.financialproduct.insurance;

import ch.supsi.dti.miniproject.financialproduct.FinancialProduct;
import ch.supsi.dti.miniproject.person.Banker;
import ch.supsi.dti.miniproject.person.Person;

import java.time.LocalDate;

public abstract class Insurance extends FinancialProduct {

    private final double annualFee;

    public Insurance(LocalDate openingDate, Banker banker, Person customer, double annualFee) {
        super(openingDate, banker, customer);
        this.annualFee = annualFee;
    }

    public double getAnnualFee() {
        return this.annualFee;
    }
}
