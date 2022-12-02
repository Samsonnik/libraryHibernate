package ru.samsonnik.library.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "book_name")
    @NotEmpty(message = "Cannot be a null")
    @Size(min = 3, max = 30, message = "Cannot be less than 5 or greater than 10 characters")
    private String bookName;

    @Column(name = "book_author")
    @NotEmpty(message = "Cannot be a null")
    @Size(min = 3, max = 30, message = "Cannot be less than 5 or greater than 10 characters")
    private String bookAuthor;

    @Column(name = "year")
    @Min(value = 1960, message = "Too old book")
    @Max(value = 2022, message = "Too big value")
    @NotNull(message = "cannot be a null")
    private int year;

    @Column(name = "was_taken")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date wasTakenDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Person owner;

    public Book(String bookName, String bookAuthor, int year) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.year = year;
    }

    public Book() {
    }


    public Date getWasTakenDate() {
        return wasTakenDate;
    }

    public void setWasTakenDate(Date wasTakenDate) {
        this.wasTakenDate = wasTakenDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && year == book.year && Objects.equals(bookName, book.bookName) && Objects.equals(bookAuthor, book.bookAuthor) && Objects.equals(owner, book.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookName, bookAuthor, year, owner);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", year=" + year +
                ", owner=" + owner +
                '}';
    }
}
