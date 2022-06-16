package ch.supsi.dti.miniproject.financialproduct.fiscalsurveillace;

import ch.supsi.dti.miniproject.exception.SupervisorAlreadySetException;
import ch.supsi.dti.miniproject.exception.SupervisorInvalidException;
import ch.supsi.dti.miniproject.person.SurveillanceSupervisor;

import java.io.Serializable;

public interface Surveillable{

    SurveillanceSupervisor getSupervisor();

    void setSupervisor(SurveillanceSupervisor supervisor) throws SupervisorAlreadySetException, SupervisorInvalidException;

    boolean isUnderSurveillance();

    void setSurveillanceEnabled(boolean what);
}
