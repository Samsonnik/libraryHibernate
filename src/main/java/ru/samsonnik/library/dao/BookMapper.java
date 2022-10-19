package ru.samsonnik.library.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.samsonnik.library.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setOwnerId(rs.getInt("owner_id"));
        book.setBookName(rs.getString("book_name"));
        book.setBookAuthor(rs.getString("book_author"));
        book.setYear(rs.getInt("year"));
        return book;
    }
}
