package ch.supsi.dti.miniproject.financialproduct.insurance;

import ch.supsi.dti.miniproject.person.Banker;
import ch.supsi.dti.miniproject.person.Person;

import java.time.LocalDate;

public class CarInsurance extends Insurance {
    public CarInsurance(LocalDate openingDate, Banker banker, Person customer, double annualFee) {
        super(openingDate, banker, customer, annualFee);
    }
}
