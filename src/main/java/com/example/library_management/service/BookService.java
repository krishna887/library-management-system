package com.example.library_management.service;

import com.example.library_management.dto.BookRequestDto;
import com.example.library_management.dto.BookResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;


public interface BookService {
    BookResponseDto createBook(BookRequestDto book);

    BookResponseDto deleteBook(BookRequestDto book);

    BookResponseDto updateBook(long id, BookRequestDto book);

    List<BookResponseDto> findBookByTitle(String title);

    List<BookResponseDto> findBookByAuthor(String author);

    List<BookResponseDto> findBookByIsbn(String isbn);

    Page<BookResponseDto> findAllBooks(int pageNo, int pageSize);
}
