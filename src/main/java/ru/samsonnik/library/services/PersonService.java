package ru.samsonnik.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.samsonnik.library.model.Book;
import ru.samsonnik.library.model.Person;
import ru.samsonnik.library.repositories.PersonRepository;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    @Transactional
    public void updateById(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        personRepository.save(updatedPerson);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(int id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElse(null);
    }

    public Map<Book, Boolean> getBookList(int id) {
        Optional<Person> person = personRepository.findById(id);
        Map<Book, Boolean> bookMap = new HashMap<>();
        if (person.isPresent()) {
            List<Book> bookList = person.get().getBookList();
            bookList.forEach(book -> {
                Boolean lateReturn = Boolean.FALSE;
                Date checkDate = addTenDays(book.getWasTakenDate());
                if (checkDate.before(new Date())) {
                    lateReturn = Boolean.TRUE;
                }
                bookMap.put(book, lateReturn);
            });
        }
        return bookMap;
    }

    private Date addTenDays(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 10);
        return calendar.getTime();
    }
}
