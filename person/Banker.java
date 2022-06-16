package ch.supsi.dti.miniproject.person;

public class Banker extends Person {
    final private String branchName;

    public Banker(String firstName, String lastName, String branchName) {
        super(firstName, lastName);
        this.branchName = branchName;
    }

    public String getBranchName() {
        return branchName;
    }
}
