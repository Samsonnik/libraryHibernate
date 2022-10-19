package ru.samsonnik.library.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.samsonnik.library.dao.PersonDao;
import ru.samsonnik.library.model.Person;

@Component
public class PersonValidator implements Validator {

    private final PersonDao personDao;

    @Autowired
    public PersonValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (personDao.index().stream().filter(value -> value.getLastName().equals(person.getLastName())).count() >= 2) {
            errors.rejectValue("firstName", "", "This person already exists");
        }
    }
}
