package ru.samsonnik.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.samsonnik.library.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByBookNameIsContainingIgnoreCaseOrderByYear(String bookName);

    List<Book> findBookByOwnerNotNull();
}
