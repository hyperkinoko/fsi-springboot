package com.example.milestone;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
    private List<Book> books = new ArrayList<>();
    
    @GetMapping("books")
    public String getBooks(Model model) {
        model.addAttribute("books", this.books);
        return "book-list";
    }

    @GetMapping("books/add")
    public String getAddBook(Model model) {
        model.addAttribute("input", new PostBookRequest());
        return "book-add";
    }

    @PostMapping("books/add")
    public String postAddBook(@ModelAttribute PostBookRequest input, Model model) {
        Book book = new Book(input.getTitle(), input.getAuthor(), input.getNumOfPages(), input.getClassification());
        this.books.add(book);
        for(Book b : books) {
            System.out.println(b);
        }
        model.addAttribute("book", book);
        return "redirect:/books";
    }
}
