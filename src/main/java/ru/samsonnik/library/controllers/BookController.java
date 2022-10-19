package ru.samsonnik.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.samsonnik.library.dao.BookDao;
import ru.samsonnik.library.dao.PersonDao;
import ru.samsonnik.library.model.Book;
import ru.samsonnik.library.util.BookValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/library")
public class BookController {
    private final BookDao bookDao;
    private final PersonDao personDao;
    private final BookValidator validator;

    @Autowired
    public BookController(BookDao bookDao, PersonDao personDao, BookValidator validator) {
        this.validator = validator;
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping("/books")
    public String index(Model model) {
        model.addAttribute("books", bookDao.index());
        return "books/index";
    }

    @GetMapping("/home_page")
    public String homePage(Model model) {
        return "homePage";
    }

    @GetMapping("/books/{id}")
    public String bookPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDao.getBook(id));
        model.addAttribute("people", personDao.index());
        model.addAttribute("owner", bookDao.getOwnerName(id));
        return "books/getBook";
    }

    @PostMapping("books/update/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        validator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/getBook";
        }
        bookDao.update(id, book);
        return "redirect:/library/books";
    }

    @GetMapping("/books/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDao.delete(id);
        return "redirect:/library/books";
    }

    @GetMapping("/books/create")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping("/books/new")
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult result) {
        validator.validate(book, result);
        if (result.hasErrors()) {
            return "books/new";
        }
        bookDao.save(book);
        return "redirect:/library/books";
    }

    @GetMapping("/books/unpin/{id}")
    public String unpinBook(@PathVariable("id") int id) {
        bookDao.unpinBook(id);
        return "redirect:/library/books";
    }
}
