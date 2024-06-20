package com.bookModel.bookmodelapplication.controller;

import com.bookModel.bookmodelapplication.model.Book;
import com.bookModel.bookmodelapplication.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


    @RestController
    @RequestMapping("/api/books")
    public class Bookcontroller {

        @Autowired
        private BookService bookService;

        @PostMapping
        public Book createBook(@RequestBody Book book) {
            return bookService.createBook(book);
        }

        @GetMapping
        public List<Book> getAllBooks() {
            return bookService.getAllBooks();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Book> getBookById(@PathVariable Long id) {
            return bookService.getBookById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PutMapping("/{id}")
        public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
            return ResponseEntity.ok(bookService.updateBook(id, bookDetails));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        }
    }

