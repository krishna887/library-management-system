package com.example.library_management.controller;

import com.example.library_management.dto.BookRequestDto;
import com.example.library_management.dto.BookResponseDto;
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

    @PostMapping("/")//done by librarian
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto book){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(book));
    }
    @DeleteMapping("/{id}")//done by librarian
    public ResponseEntity<?> deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }
    @PutMapping("/{id}") //done by librarian
    public ResponseEntity<BookResponseDto>updateBooks(@PathVariable long id, @RequestBody BookRequestDto book){
      return   ResponseEntity.ok().body(bookService.updateBook(id, book));
    }
    @GetMapping("/findBooksByAuthor") //done by both
    public ResponseEntity<List<BookResponseDto>> findBooksByAuthor(@RequestParam String author){
        return ResponseEntity.ok().body(bookService.findBookByAuthor(author));
    }
    @GetMapping("/findBooksByTitle") //done by both
    public ResponseEntity<List<BookResponseDto>> findBooksByTitle(@RequestParam String title){
        return ResponseEntity.ok().body(bookService.findBookByTitle(title));
    }
    @GetMapping("/findBooksByIsbn") // done by both
    public ResponseEntity<List<BookResponseDto>> findBooksByIsbn(@RequestParam String isbn){
        return ResponseEntity.ok().body(bookService.findBookByIsbn(isbn));
    }

}
