package com.example.library_management.controller;

import com.example.library_management.dto.BookRequestDto;
import com.example.library_management.dto.BookResponseDto;
import com.example.library_management.response.GenericResponse;
import com.example.library_management.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @PostMapping("/create")//done by librarian
    public ResponseEntity<GenericResponse<BookResponseDto>> createBook( @Valid @RequestBody BookRequestDto book) {
        return ResponseEntity.status(HttpStatus.CREATED).header("custom header", "header can be passed herr").body(GenericResponse.success(bookService.createBook(book), "Book Created successfully", HttpStatus.CREATED, HttpStatus.CREATED.value()));
    }
    @DeleteMapping("/delete")//done by librarian
    public ResponseEntity<GenericResponse<?>> deleteBook( @Valid @RequestBody BookRequestDto book) {
        return ResponseEntity.status(HttpStatus.GONE).header("delete_Header", "deleted").body(GenericResponse.success(bookService.deleteBook(book), "Book Deleted ", HttpStatus.GONE, HttpStatus.GONE.value()));
    }

    @PutMapping("/update/{id}") //done by librarian
    public ResponseEntity<GenericResponse<BookResponseDto>> updateBooks(@PathVariable long id,@Valid @RequestBody BookRequestDto book) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("update_header", "update").body(GenericResponse.success(bookService.updateBook(id, book), "Book Updated done", HttpStatus.ACCEPTED, HttpStatus.ACCEPTED.value()));
    }

    @GetMapping("/findBooksByAuthor/{author}") //done by both
    public ResponseEntity<GenericResponse<List<BookResponseDto>>> findBooksByAuthor(@PathVariable String author) {
        log.error("Inside controller {}", author);
        return ResponseEntity.status(HttpStatus.OK).header("findByAuthor", "find_By_author header").body(GenericResponse.success(bookService.findBookByAuthor(author), "Find Book by Author", HttpStatus.OK, HttpStatus.OK.value()));

    }

    @GetMapping("/findBooksByTitle") //done by both
    public ResponseEntity<GenericResponse<List<BookResponseDto>>> findBooksByTitle(@RequestParam String title) {
        return ResponseEntity.status(HttpStatus.OK).header("findByTitle", "find_by_title").body(GenericResponse.success(bookService.findBookByTitle(title), "Find Book by Title", HttpStatus.OK, HttpStatus.OK.value()));

    }

    @GetMapping("/findBooksByIsbn") // done by both
    public ResponseEntity<GenericResponse<List<BookResponseDto>>> findBooksByIsbn(@RequestParam String isbn) {
        return ResponseEntity.status(HttpStatus.OK).header("findBooksByIsbn", "find-books-by-id").body(GenericResponse.success(bookService.findBookByIsbn(isbn), "Find Book by ISBN", HttpStatus.OK, HttpStatus.OK.value()));
    }

}
