package ru.samsonnik.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.samsonnik.library.model.Book;
import ru.samsonnik.library.model.Person;

import java.util.List;

@Component
public class PersonDao {
//    private final JdbcTemplate template;
//
//    @Autowired
//    public PersonDao(JdbcTemplate template) {
//        this.template = template;
//    }
//
//    public void save(Person person) {
//        template.update("insert into people(first_name, last_name, years_old) values (?, ?, ?)",
//                person.getFirstName(), person.getLastName(), person.getYearsOld());
//    }
//
//    public void delete(int id) {
//        template.update("delete from people where id = ?", id);
//    }
//
//    public void update(int id, Person person) {
//        template.update("update people set first_name = ?, last_name = ?, years_old = ? where id = ?",
//                person.getFirstName(), person.getLastName(), person.getYearsOld(), id);
//    }
//
//    public List<Person> index() {
//        return template.query("select * from people", new PersonMapper());
//    }
//
//    public Person getPerson(int id) {
//        return template.query("select * from people where id = ?", new Object[]{id}, new PersonMapper())
//                .stream()
//                .findFirst()
//                .orElse(null);
//    }
//
//    public List<Book> getBooks(int id) {
//        return template.query("select * from books join people p on books.owner_id = p.id where owner_id = ?",
//                new Object[]{id}, new BookMapper());
//    }
}
