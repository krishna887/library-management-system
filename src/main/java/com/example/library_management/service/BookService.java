package com.example.library_management.service;

import com.example.library_management.entity.Book;
import com.example.library_management.entity.User;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }


    public Book updateBook(long id, Book book) {
        return bookRepository.findById(id).map(book1 -> {
            book1.setTitle(book.getTitle());
            book1.setAuthor(book.getAuthor());
            book1.setIsbn(book.getIsbn());
            book1.setCopiesAvailable(book.getCopiesAvailable());
            return bookRepository.save(book1);
        }).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }
   public List<Book> findBookByTitle(String title){
       return bookRepository.findByTitleContaining(title);
    }
    public List<Book> findBookByAuthor(String author){
        return bookRepository.findByAuthorContaining(author);
    }
    public List<Book> findBookByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn);
    }
}
