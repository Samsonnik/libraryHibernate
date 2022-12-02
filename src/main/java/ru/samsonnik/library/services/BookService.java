package ru.samsonnik.library.services;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.samsonnik.library.model.Book;
import ru.samsonnik.library.model.Person;
import ru.samsonnik.library.repositories.BookRepository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void updateById(int id, Book updatedBook) {
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findByBookNameAndOrderByYear(String bookName) {
        return bookRepository.findByBookNameIsContainingIgnoreCaseOrderByYear(bookName);
    }

    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear) {
            return bookRepository.findAll(Sort.by("year"));
        } else {
            return bookRepository.findAll();
        }
    }

    public Book findById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    public List<Book> findAllWithOwner() {
        return bookRepository.findBookByOwnerNotNull();
    }

    @Transactional
    public void unPinBook(int id) {
        Book book = findById(id);
        book.setOwner(null);
        updateById(id, book);
    }

    @Transactional
    public void setPersonAndTakenDate(Integer id, Person selectedPerson, Date takenDate) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(value -> {
            value.setOwner(selectedPerson);
            value.setWasTakenDate(takenDate);
        });
    }
}
