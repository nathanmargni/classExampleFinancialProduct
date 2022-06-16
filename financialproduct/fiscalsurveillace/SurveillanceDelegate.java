package ch.supsi.dti.miniproject.financialproduct.fiscalsurveillace;

import ch.supsi.dti.miniproject.exception.SupervisorAlreadySetException;
import ch.supsi.dti.miniproject.exception.SupervisorInvalidException;
import ch.supsi.dti.miniproject.financialproduct.FinancialProduct;
import ch.supsi.dti.miniproject.person.SurveillanceSupervisor;

import java.io.Serializable;

public class SurveillanceDelegate implements Surveillable, Serializable {

    private static final long serialVersionUID = 42365467235467235L;

    private final Surveillable relatedProduct;
    private SurveillanceSupervisor supervisor;
    private boolean surveillanceEnabled = false;

    public SurveillanceDelegate(Surveillable product) {
        this.relatedProduct = product;
    }

    @Override
    public SurveillanceSupervisor getSupervisor() {
        return this.supervisor;
    }

    @Override
    public void setSupervisor(SurveillanceSupervisor supervisor) throws SupervisorAlreadySetException, SupervisorInvalidException {
        if (this.supervisor != null)
            throw new SupervisorAlreadySetException((FinancialProduct) relatedProduct, relatedProduct.getSupervisor(), supervisor);

        if (this.supervisor.equals(((FinancialProduct) relatedProduct).getCustomer()))
            throw new SupervisorInvalidException((FinancialProduct) relatedProduct);
        this.supervisor = supervisor;
    }

    @Override
    public boolean isUnderSurveillance() {
        return this.surveillanceEnabled;
    }

    @Override
    public void setSurveillanceEnabled(boolean what) {
        this.surveillanceEnabled = what;
    }
}
