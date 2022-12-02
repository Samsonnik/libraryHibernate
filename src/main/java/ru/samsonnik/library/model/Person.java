package ru.samsonnik.library.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "people")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    @NotEmpty(message = "Cannot be a null")
    @Size(min = 3, max = 30, message = "Cannot be less than 5 or greater than 10 characters")
    private String firstName;
    @Column(name = "last_name")
    @NotEmpty(message = "Cannot be a null")
    @Size(min = 3, max = 30, message = "Cannot be less than 5 or greater than 10 characters")
    private String lastName;
    @Column(name = "years_old")
    @Min(value = 18, message = "Too young person")
    @Max(value = 70, message = "you are to old for this shit")
    @NotNull(message = "cannot be a null")
    private int yearsOld;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Book> bookList;

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

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && yearsOld == person.yearsOld && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(bookList, person.bookList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, yearsOld, bookList);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yearsOld=" + yearsOld + '}';
    }
}
