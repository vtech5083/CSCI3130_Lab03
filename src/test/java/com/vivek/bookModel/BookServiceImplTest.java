package com.vivek.bookModel;


import com.vivek.bookModel.Repository.BookRepository;
import com.vivek.bookModel.model.Book;
import com.vivek.bookModel.services.implementation.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Existing test cases...

    @Test
    void shouldGetBookByTitle() {
        // Arrange
        String title = "Test Title";
        Book testBook = new Book();
        testBook.setTitle(title);
        testBook.setAuthor("Test Author");
        testBook.setIsbn("1234567890");
        when(bookRepository.findByTitle(title)).thenReturn(Optional.of(testBook));

        // Act
        Optional<Book> result = bookService.getBookByTitle(title);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo(title);
        verify(bookRepository, times(1)).findByTitle(title);
    }


    @Test
    void shouldUpdateBookAuthor() {
        // Test case implementation...
    }
}
