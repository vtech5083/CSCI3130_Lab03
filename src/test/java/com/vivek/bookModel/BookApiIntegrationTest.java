package com.vivek.bookModel;


import com.vivek.bookModel.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BookApiIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/api/books";
    }

    @Test
    public void createBook() {
        Book book = new Book("Test Book", "Test Author", "1234567890");
        ResponseEntity<Book> responseEntity = restTemplate.postForEntity(baseUrl, book, Book.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getTitle()).isEqualTo("Test Book");
    }

    @Test
    public void getAllBooks() {
        Book book = new Book("Test Book", "Test Author", "1234567890");
        restTemplate.postForEntity(baseUrl, book, Book.class);

        ResponseEntity<Book[]> responseEntity = restTemplate.getForEntity(baseUrl, Book[].class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotEmpty();
    }

    @Test
    public void getBookById() {
        Book book = new Book("Test Book", "Test Author", "1234567890");
        ResponseEntity<Book> postResponse = restTemplate.postForEntity(baseUrl, book, Book.class);

        Long bookId = postResponse.getBody().getId();
        ResponseEntity<Book> responseEntity = restTemplate.getForEntity(baseUrl + "/" + bookId, Book.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getId()).isEqualTo(bookId);
    }

    @Test
    public void updateBook() {
        Book book = new Book("Test Book", "Test Author", "1234567890");
        ResponseEntity<Book> postResponse = restTemplate.postForEntity(baseUrl, book, Book.class);

        Long bookId = postResponse.getBody().getId();
        book.setTitle("Updated Title");
        HttpEntity<Book> requestUpdate = new HttpEntity<>(book, new HttpHeaders());

        ResponseEntity<Book> responseEntity = restTemplate.exchange(baseUrl + "/" + bookId, HttpMethod.PUT, requestUpdate, Book.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getTitle()).isEqualTo("Updated Title");
    }

    @Test
    public void deleteBook() {
        Book book = new Book("Test Book", "Test Author", "1234567890");
        ResponseEntity<Book> postResponse = restTemplate.postForEntity(baseUrl, book, Book.class);

        Long bookId = postResponse.getBody().getId();
        restTemplate.delete(baseUrl + "/" + bookId);

        ResponseEntity<Book> responseEntity = restTemplate.getForEntity(baseUrl + "/" + bookId, Book.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}