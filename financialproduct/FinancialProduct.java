package ch.supsi.dti.miniproject.financialproduct;

import ch.supsi.dti.miniproject.person.Banker;
import ch.supsi.dti.miniproject.person.Person;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class FinancialProduct implements Serializable {

    private static final long serialVersionUID = 42365467235467235L;
    private final Person customer;
    private final LocalDate openingDate;
    private Banker banker;

    public FinancialProduct(LocalDate openingDate, Banker banker, Person customer) {
        this.openingDate = openingDate;
        this.banker = banker;
        this.customer = customer;
    }

    public Person getCustomer() {
        return customer;
    }

    public Banker getBanker() {
        return banker;
    }

    public FinancialProduct setBanker(Banker banker) {
        this.banker = banker;
        return this;
    }

    public LocalDate getOpeningDate() {
        // LocalDate objects are immutable, so it is safe to return the instance
        return this.openingDate;
    }
}
