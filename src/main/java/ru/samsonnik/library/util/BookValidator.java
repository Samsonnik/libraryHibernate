package ru.samsonnik.library.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.samsonnik.library.dao.BookDao;
import ru.samsonnik.library.model.Book;

@Component
public class BookValidator implements Validator {

    private final BookDao bookDao;

    @Autowired
    public BookValidator(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
//        Book book = (Book) target;
//        if (bookDao.index().stream().filter(value -> value.getBookName().equals(book.getBookName()) &&
//                value.getBookAuthor().equals(book.getBookAuthor())).count() >= 2) {
//            errors.rejectValue("bookName", "", "This book already exists");
//        }
    }
}
