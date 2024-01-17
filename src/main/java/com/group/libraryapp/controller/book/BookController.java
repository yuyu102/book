package com.group.libraryapp.controller.book;

import com.group.libraryapp.service.book.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;
    // 원래는 = new bookService이었는데 생성자를 통해 bookservice를 받음

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    // 생성자를 들고옴.

    @PostMapping("/book")
    public void saveBook() {
        bookService.saveBook(); }
}
