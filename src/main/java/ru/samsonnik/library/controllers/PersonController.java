package ru.samsonnik.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.samsonnik.library.dao.PersonDao;
import ru.samsonnik.library.model.Person;
import ru.samsonnik.library.services.PersonService;
import ru.samsonnik.library.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/library")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/people")
    public String index(Model model) {
        model.addAttribute("people", personService.findAll());
        return "person/index";
    }

    @GetMapping("/people/{id}")
    public String peoplePage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.findById(id));
        model.addAttribute("books", personService.getBookList(id));
             return "person/getPerson";
    }

    @GetMapping("/people/create")
    public String newBook(@ModelAttribute("person") Person person) {
        return "person/new";
    }

    @PostMapping("/people/new")
    public String create(@ModelAttribute("person") Person person) {
        personService.save(person);
        return "redirect:/library/people";
    }

    @GetMapping("/people/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/library/people";
    }

    @PostMapping("/people/update/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "/person/getPerson";
        }
        personService.updateById(id, person);
        return "redirect:/library/people";
    }
}
