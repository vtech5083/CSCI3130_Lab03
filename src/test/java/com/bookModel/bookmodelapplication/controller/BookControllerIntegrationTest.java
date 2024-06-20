package com.bookModel.bookmodelapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.bookModel.bookmodelapplication.model.Book;
import com.bookModel.bookmodelapplication.repository.Bookrepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Bookrepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();
    }


    @Test
    public void shouldCreateBook() throws Exception {
        Book book = new Book("Test Title", "Test Author", "1234567890");

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(book.getTitle())))
                .andExpect(jsonPath("$.author", is(book.getAuthor())))
                .andExpect(jsonPath("$.isbn", is(book.getIsbn())));

        // Verify the book was created
        Optional<Book> createdBook = bookRepository.findByTitle("Test Title");
        assertThat(createdBook).isPresent();
        assertThat(createdBook.get().getAuthor()).isEqualTo("Test Author");
        assertThat(createdBook.get().getIsbn()).isEqualTo("1234567890");
    }

    @Test
    public void shouldGetAllBooks() throws Exception {
        Book book1 = new Book("Test Title 1", "Test Author 1", "1234567891");
        Book book2 = new Book("Test Title 2", "Test Author 2", "1234567892");
        bookRepository.save(book1);
        bookRepository.save(book2);

        mockMvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].title", is("Test Title 1")))
                .andExpect(jsonPath("$[1].title", is("Test Title 2")));
    }

    @Test
    public void shouldGetBookById() throws Exception {
        Book book = new Book("Test Title", "Test Author", "1234567890");
        Book savedBook = bookRepository.save(book);

        mockMvc.perform(get("/api/books/{id}", savedBook.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(book.getTitle())))
                .andExpect(jsonPath("$.author", is(book.getAuthor())))
                .andExpect(jsonPath("$.isbn", is(book.getIsbn())));
    }

    @Test
    public void shouldUpdateBook() throws Exception {
        Book book = new Book("Test Title", "Test Author", "1234567890");
        Book savedBook = bookRepository.save(book);

        Book updatedBook = new Book("Updated Title", "Updated Author", "0987654321");

        mockMvc.perform(put("/api/books/{id}", savedBook.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(updatedBook.getTitle())))
                .andExpect(jsonPath("$.author", is(updatedBook.getAuthor())))
                .andExpect(jsonPath("$.isbn", is(updatedBook.getIsbn())));

        // Verify the book was updated
        Optional<Book> foundBook = bookRepository.findById(savedBook.getId());
        assertThat(foundBook).isPresent();
        assertThat(foundBook.get().getTitle()).isEqualTo("Updated Title");
        assertThat(foundBook.get().getAuthor()).isEqualTo("Updated Author");
        assertThat(foundBook.get().getIsbn()).isEqualTo("0987654321");
    }

    @Test
    public void shouldDeleteBook() throws Exception {
        Book book = new Book("Test Title", "Test Author", "1234567890");
        Book savedBook = bookRepository.save(book);

        mockMvc.perform(delete("/api/books/{id}", savedBook.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Optional<Book> deletedBook = bookRepository.findById(savedBook.getId());
        assertThat(deletedBook).isEmpty();
    }
}