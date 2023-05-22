package com.example.milestone.book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BookController {
    private List<Book> books = new ArrayList<>();

    @GetMapping("books")
    public String getBooks(Model model) {
        List<Book> books = findAll();
        model.addAttribute("books", books);
        return "book/book-list";
    }

    @GetMapping("books/add")
    public String getAddBook(Model model) {
        model.addAttribute("input", new PostBookRequest());
        return "book/book-add";
    }

    @PostMapping("books/add")
    public String postAddBook(@ModelAttribute PostBookRequest input) {
        createBook(input);
        return "redirect:/books";
    }

    @GetMapping("books/{id}")
    public String getBookDetail(@PathVariable String id, Model model) {
        Book book = findBookById(id);
        model.addAttribute("book", book);
        return "book/book-detail";
    }

    @GetMapping("books/{id}/delete")
    public String getBookDelete(@PathVariable String id) {
        deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("books/{id}/edit")
    public String getEditBook(@PathVariable String id, Model model) {
        Book book = findBookById(id);

        // 早期リターン
        if(book == null) {
            return "redirect:/books";
        }

        PostBookRequest input = new PostBookRequest();
        input.setTitle(book.getTitle());
        input.setAuthor(book.getAuthor());
        input.setNumOfPages(book.getNumOfPages());
        input.setClassification(book.getClassification().ordinal());
        model.addAttribute("input", input);
        model.addAttribute("id", id);
        return "book/book-edit";
    }

    @PostMapping("books/{id}/edit")
    public String postEditBook(@PathVariable String id, @ModelAttribute PostBookRequest input) {
        Book book = updateBook(id, input);
        if(book == null) {
            return "redirect:/books";
        }
        return "redirect:/books/" + id;
    }

    private Book createBook(PostBookRequest input) {
        Book book = new Book(input.getTitle(), input.getAuthor(), input.getNumOfPages(), input.getClassification());
        this.books.add(book);
        return book;
    }

    private List<Book> findAll() {
        return this.books;
    }

    private Book findBookById(String id) {
        for(Book book : this.books) {
            if(book.getId().equals(id)) {
                //見つかったとき
                return book;
            }
        }
        // booksの最後まで見て，見つからなかったとき
        return null;
    }

    private Book updateBook(String id, PostBookRequest input) {
        Book book = findBookById(id);

        if(book == null) {
            return null;
        }

        book.setTitle(input.getTitle());
        book.setAuthor(input.getAuthor());
        book.setNumOfPages(input.getNumOfPages());
        book.setClassification(BookClassification.fromNumber(input.getClassification()));
        
        return book;
    }

    private boolean deleteBookById(String id) {
        return this.books.removeIf(book -> book.getId().equals(id));
    }
}
