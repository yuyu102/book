package com.group.libraryapp.service.book;

import com.group.libraryapp.repositoty.book.BookMemoryRepository;
import com.group.libraryapp.repositoty.book.BookMysqlRepository;
import com.group.libraryapp.repositoty.book.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private  final BookRepository bookRepository;
    //        = new BookMysqlRepository();

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
// 생성자를 받음
    public void saveBook() {
        bookRepository.saveBook();
    }
}
