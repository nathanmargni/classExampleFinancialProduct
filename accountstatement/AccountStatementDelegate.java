package ch.supsi.dti.miniproject.accountstatement;

import ch.supsi.dti.miniproject.exception.AccountStatementException;
import ch.supsi.dti.miniproject.financialproduct.account.Account;
import ch.supsi.dti.miniproject.financialproduct.account.CurrentAccount;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccountStatementDelegate implements AccountStatement, Serializable {
    private static final long serialVersionUID = 42365467235467235L;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    private final Account account;

    public AccountStatementDelegate(Account account) {
        this.account = account;
    }

    @Override
    public void generateStatement() throws AccountStatementException {
        String accountType = account instanceof CurrentAccount ? "Current" : "Savings";
        String fileName = LocalDateTime.now().format(formatter) + "_" + this.account.getCustomer().getLastName() + "_" + accountType + ".txt";
        File file = new File(fileName);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(accountType + " account number " + this.account.getAccountNumber() + " statement for customer " + this.account.getCustomer() + "\n");
            bw.write("Banker: " + this.account.getBanker() + "\n");
            bw.write("Balance: " + this.account.getBalance() + "\n");
        } catch (IOException e) {
            throw new AccountStatementException(account, e);
        }
    }
}
