package ru.samsonnik.library.model;

import javax.validation.constraints.*;
import java.util.List;

public class Person {
    private int id;
    @NotEmpty(message = "Cannot be a null")
    @Size(min = 3, max = 30, message = "Cannot be less than 5 or greater than 10 characters")
    private String firstName;
    @NotEmpty(message = "Cannot be a null")
    @Size(min = 3, max = 30, message = "Cannot be less than 5 or greater than 10 characters")
    private String lastName;
    @Min(value = 18, message = "Too young person")
    @NotNull(message = "cannot be a null")
    private int yearsOld;

    public Person(int id, String firstName, String lastName, int yearsOld) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearsOld = yearsOld;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYearsOld() {
        return yearsOld;
    }

    public void setYearsOld(int yearsOld) {
        this.yearsOld = yearsOld;
    }
}
