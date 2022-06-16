package ch.supsi.dti.miniproject.exception;

import ch.supsi.dti.miniproject.financialproduct.FinancialProduct;
import ch.supsi.dti.miniproject.person.SurveillanceSupervisor;

public class SupervisorAlreadySetException extends SupervisorException {

    private final SurveillanceSupervisor actualSupervisor;
    private final SurveillanceSupervisor requiredSupervisor;

    public SupervisorAlreadySetException(FinancialProduct product, SurveillanceSupervisor actualSupervisor, SurveillanceSupervisor requiredSupervisor) {
        super(product, "This product has already a supervisor");
        this.actualSupervisor = actualSupervisor;
        this.requiredSupervisor = requiredSupervisor;
    }
}
