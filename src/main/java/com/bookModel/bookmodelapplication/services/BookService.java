package com.bookModel.bookmodelapplication.services;

import com.bookModel.bookmodelapplication.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {

    Book createBook(Book book);
    List<Book> getAllBooks();
    Optional<Book> getBookById(Long id);
    Book updateBook(Long id, Book bookDetails);
    void deleteBook(Long id);

    Optional<Book> getBookByTitle(String title);
    Book updateBookAuthor(Long id, String author);
}
