package com.bookModel.bookmodelapplication.repository;

import com.bookModel.bookmodelapplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Bookrepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
}
