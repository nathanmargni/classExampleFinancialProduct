package ch.supsi.dti.miniproject.person;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = 42365467235467235L;

    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}
