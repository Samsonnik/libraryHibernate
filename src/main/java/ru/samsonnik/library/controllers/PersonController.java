package ru.samsonnik.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.samsonnik.library.dao.PersonDao;
import ru.samsonnik.library.model.Book;
import ru.samsonnik.library.model.Person;
import ru.samsonnik.library.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/library")
public class PersonController {

    private final PersonDao personDao;
    private final PersonValidator validator;

    @Autowired
    public PersonController(PersonDao personDao, PersonValidator validator) {
        this.validator = validator;
        this.personDao = personDao;
    }

    @GetMapping("/people")
    public String index(Model model) {
        model.addAttribute("people", personDao.index());
        return "person/index";
    }

    @GetMapping("/people/{id}")
    public String peoplePage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.getPerson(id));
        model.addAttribute("books", personDao.getBooks(id));
        return "person/getPerson";
    }

    @GetMapping("/people/create")
    public String newBook(@ModelAttribute("person") Person person) {
        return "person/new";
    }

    @PostMapping("/people/new")
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        validator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "person/new";
        }
        personDao.save(person);
        return "redirect:/library/people";
    }

    @GetMapping("/people/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        personDao.delete(id);
        return "redirect:/library/people";
    }

    @PostMapping("/people/update/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        validator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "person/getPerson";
        }
        personDao.update(id,person);
        return "redirect:/library/people";
    }
}
