package com.vivek.bookModel.services;

import com.vivek.bookModel.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    Book createBook(Book book);
    List<Book> getAllBooks();
    Optional<Book> getBookById(Long id);
    Book updateBook(Long id, Book bookDetails);
    void deleteBook(Long id);
}