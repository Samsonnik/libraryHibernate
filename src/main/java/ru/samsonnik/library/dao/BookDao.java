package ru.samsonnik.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.samsonnik.library.model.Book;
import ru.samsonnik.library.model.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {
//
//    private final JdbcTemplate template;
//
//    @Autowired
//    public BookDao(JdbcTemplate template) {
//        this.template = template;
//    }
//
//    public void save(Book book) {
//        template.update("insert into books(book_name, book_author, year) values (?, ?, ?)",
//                book.getBookName(), book.getBookAuthor(), book.getYear());
//    }
//
//    public void delete(int id) {
//        template.update("delete from books where id = ?", id);
//    }
//
//    public void update(int id, Book book) {
//        template.update("update books set book_name = ?, book_author = ?, year = ?, owner_id = ? where id = ?",
//                book.getBookName(), book.getBookAuthor(), book.getYear(), book.getOwnerId(), id);
//    }
//
//    public List<Book> index() {
//        return template.query("select * from books", new BookMapper());
//    }
//
//    public Book getBook(int id) {
//        return template.query("select * from books where id = ?", new Object[]{id}, new BookMapper())
//                .stream()
//                .findFirst()
//                .orElse(null);
//    }
//
//    public Person getOwnerName(int bookId) {
//        return template.query("select * from people p join books b " +
//                        "on p.id = b.owner_id where b.id = ?", new Object[]{bookId},
//                new PersonMapper()).stream().findFirst().orElse(null);
//    }
//
//    public void unpinBook(int id) {
//        template.update("update books set owner_id = null where id = ?", id);
//    }
}
