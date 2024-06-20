package com.bookModel.bookmodelapplication.services.implementation;

import com.bookModel.bookmodelapplication.model.Book;
import com.bookModel.bookmodelapplication.repository.Bookrepository;
import com.bookModel.bookmodelapplication.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private Bookrepository bookrepository;

    @Override
    public Book createBook(Book book) {
        return bookrepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookrepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookrepository.findById(id);
    }

    @Override
    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookrepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        return bookrepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookrepository.deleteById(id);
    }

    @Override
    public Optional<Book> getBookByTitle(String title) {
        return bookrepository.findByTitle(title);
    }

    @Override
    public Book updateBookAuthor(Long id, String author) {
        Book book = bookrepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setAuthor(author);
        return bookrepository.save(book);
    }
}
