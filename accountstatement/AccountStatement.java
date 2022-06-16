package ch.supsi.dti.miniproject.accountstatement;

import ch.supsi.dti.miniproject.exception.AccountStatementException;

import java.io.Serializable;

public interface AccountStatement{
    void generateStatement() throws AccountStatementException;
}
