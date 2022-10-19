package ru.samsonnik.library.model;

import javax.validation.constraints.*;

public class Book {

    private int id;
    private int ownerId;
    @NotEmpty(message = "Cannot be a null")
    @Size(min = 3, max = 30, message = "Cannot be less than 5 or greater than 10 characters")
    private String bookName;
    @NotEmpty(message = "Cannot be a null")
    @Size(min = 3, max = 30, message = "Cannot be less than 5 or greater than 10 characters")
    private String bookAuthor;
    @Min(value = 1960, message = "Too old book")
    @Max(value = 2022, message = "Too big value")
    @NotNull(message = "cannot be a null")
    private int year;

    public Book(int id, int owner_id, String book_name, String book_author, int year) {
        this.id = id;
        this.ownerId = owner_id;
        this.bookName = book_name;
        this.bookAuthor = book_author;
        this.year = year;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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
}
