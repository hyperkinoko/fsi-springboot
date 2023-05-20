package com.example.milestone;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
    
    @GetMapping("books")
    public String getBooks(Model model) {
        Book[] books = new Book[4];
        books[0] = new Book("Java基礎", "kinoko", 32, 5);
        books[1] = new Book("Java応用", "tanaka", 50, 5);
        books[2] = new Book("Java実践", "ichiki", 100, 5);
        books[3] = new Book("Java演習", "kinoshita", 45, 5);
        model.addAttribute("books", books);
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
        System.out.println(book);
        model.addAttribute("book", book);
        return "result";
    }
}
