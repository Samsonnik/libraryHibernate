package ru.samsonnik.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.samsonnik.library.model.Book;
import ru.samsonnik.library.model.Person;
import ru.samsonnik.library.services.BookService;
import ru.samsonnik.library.services.PersonService;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/library")
public class BookController {

    private final BookService bookService;
    private final PersonService personService;


    @Autowired
    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping("/books")
    public String index(Model model, @RequestParam(required = false, value = "valueForFinding") String valueForFinding,
                        @RequestParam(required = false, value = "sort") String sortValue) {
        if (valueForFinding != null) {
            model.addAttribute("books", bookService.findByBookNameAndOrderByYear(valueForFinding));
        } else {
            if ((sortValue != null) && (sortValue.equals("true"))) {
                model.addAttribute("books", bookService.findAll(true));
            } else {
                model.addAttribute("books", bookService.findAll());
            }
        }
        return "books/index";
    }

    @GetMapping("/home_page")
    public String homePage(Model model) {
        return "homePage";
    }

    @GetMapping("/books/{id}")
    public String bookPage(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookService.findById(id));
        Person owner = bookService.findById(id).getOwner();
        if (owner == null) {
            model.addAttribute("people", personService.findAll());
        } else {
            model.addAttribute("owner", owner);
        }
        return "books/getBook";
    }

    @PostMapping("books/update/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "books/getBook";
        }
        bookService.updateById(id, book);
        return "redirect:/library/books";
    }

    @GetMapping("/books/assign/{id}")
    public String setOwner(@PathVariable("id") int id,
                           @RequestParam(required = false, name = "wasTaken")String stringDate,
                           @RequestParam(required = false, name = "personId")int personId) {
        try {
            Date wasTaken = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
            Person selectedPerson = personService.findById(personId);
            bookService.setPersonAndTakenDate(id, selectedPerson, wasTaken);
        } catch (ParseException exception) {
            return "redirect:/library/books/" + id;
        }
        return "redirect:/library/books/" + id;
    }

    @GetMapping("/books/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.deleteById(id);
        return "redirect:/library/books";
    }

    @GetMapping("/books/create")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping("/books/new")
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/library/books/create";
        }
        bookService.save(book);
        return "redirect:/library/books";
    }

    @GetMapping("/books/unpin/{id}")
    public String unpinBook(@PathVariable("id") int id) {
        bookService.unPinBook(id);
        return "redirect:/library/books/" + id;
    }

    @GetMapping("books/search_page")
    public String searchPage(@ModelAttribute("book") Book book, @ModelAttribute("person") Person person,  Model model) {
        model.addAttribute("booksForSearch", bookService.findAllWithOwner());
        return "books/searchPage";
    }
}
