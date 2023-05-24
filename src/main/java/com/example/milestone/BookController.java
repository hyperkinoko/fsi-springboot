package com.example.milestone;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BookController {
    private final BookDao dao = new BookDao();

    @GetMapping("books")
    public String getBooks(Model model) {
        List<Book> books = dao.findAll();
        model.addAttribute("books", books);
        return "book-list";
    }

    @GetMapping("books/add")
    public String getAddBook(Model model) {
        model.addAttribute("input", new PostBookRequest());
        return "book-add";
    }

    @PostMapping("books/add")
    public String postAddBook(@ModelAttribute PostBookRequest input) {
        Book book = new Book(input.getTitle(), input.getAuthor(), input.getNumOfPages(), input.getClassification());
        dao.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("books/{id}")
    public String getBookDetail(@PathVariable String id, Model model) {
        Book book = dao.findBookById(id);
        model.addAttribute("book", book);
        return "book-detail";
    }

    @GetMapping("books/{id}/delete")
    public String getBookDelete(@PathVariable String id) {
        dao.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("books/{id}/edit")
    public String getEditBook(@PathVariable String id, Model model) {
        Book book = dao.findBookById(id);

        if(book == null) {
            return null;
        }

        PostBookRequest input = createInputFromBook(book);
        model.addAttribute("input", input);
        return "book-edit";
    }

    @PostMapping("books/{id}/edit")
    public String postEditBook(@PathVariable String id, @ModelAttribute PostBookRequest input) {
        Book book = applyInputToBook(id, input);

        if(book == null) {
            return "redirect:/books";
        }

        dao.updateBook(book);
        return "redirect:/books/" + id;
    }

    private Book applyInputToBook(String id, PostBookRequest input) {
        Book book = dao.findBookById(id);

        if(book == null) {
            return null;
        }

        book.setTitle(input.getTitle());
        book.setAuthor(input.getAuthor());
        book.setNumOfPages(input.getNumOfPages());
        book.setClassification(BookClassification.fromNumber(input.getClassification()));

        return book;
    }

    private PostBookRequest createInputFromBook(Book book) {
        PostBookRequest input = new PostBookRequest();

        input.setTitle(book.getTitle());
        input.setAuthor(book.getAuthor());
        input.setNumOfPages(book.getNumOfPages());
        input.setClassification(book.getClassification().ordinal());

        return input;
    }


}
