package com.example.library_management.repository;

import com.example.library_management.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitleAndAuthorAndIsbn(String title, String author, String isbn);

    List<Book> findByAuthorContaining(String author);

    List<Book> findByTitleContaining(String title);

    List<Book> findByIsbn(String isbn);

    Optional<Book> findBookById(long id);
}
