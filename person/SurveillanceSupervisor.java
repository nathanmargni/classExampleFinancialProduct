package ch.supsi.dti.miniproject.person;

public class SurveillanceSupervisor extends Person {
    private final String code;

    public SurveillanceSupervisor(String firstName, String lastName, String code) {
        super(firstName, lastName);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
