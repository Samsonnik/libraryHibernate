package ru.samsonnik.library.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.samsonnik.library.model.Person;
import ru.samsonnik.library.services.PersonService;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (personService.findAll().stream().filter(value -> value.getLastName().equals(person.getLastName())).count() >= 2) {
            errors.rejectValue("firstName", "", "This person already exists");
        }
    }
}
