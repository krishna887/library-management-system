package com.example.library_management.controller;

import com.example.library_management.entity.Book;
import com.example.library_management.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(book));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book>updateBooks(@PathVariable long id, @RequestBody Book book){
      return   ResponseEntity.ok().body(bookService.updateBook(id, book));
    }
    @GetMapping("/findBooksByAuthor")
    public ResponseEntity<List<Book>> findBooksByAuthor(@PathVariable String author){
        return ResponseEntity.ok().body(bookService.findBookByAuthor(author));
    }
    @GetMapping("/findBooksByTitle")
    public ResponseEntity<List<Book>> findBooksByTitle(@PathVariable String title){
        return ResponseEntity.ok().body(bookService.findBookByAuthor(title));
    }
    @GetMapping("/findBooksByIsbn")
    public ResponseEntity<List<Book>> findBooksByIsbn(@PathVariable String isbn){
        return ResponseEntity.ok().body(bookService.findBookByAuthor(isbn));
    }

}
