package com.example.milestone;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
