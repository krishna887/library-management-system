package com.example.library_management.controller;

import com.example.library_management.dto.BookRequestDto;
import com.example.library_management.dto.BookResponseDto;
import com.example.library_management.response.GenericResponse;
import com.example.library_management.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/create")//done by librarian
    public ResponseEntity<GenericResponse<BookResponseDto>> createBook(@RequestBody BookRequestDto book){
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("custom header","header can be passed herr")
                .body(GenericResponse.success(bookService.createBook(book),"Book Created successfully",HttpStatus.CREATED,HttpStatus.CREATED.value()));

    }
    @DeleteMapping("/delete")//done by librarian
    public ResponseEntity<GenericResponse<?>> deleteBook(@RequestBody BookRequestDto book){
        return ResponseEntity.status(HttpStatus.GONE)
                .header("delete_Header", "deleted")
                .body(GenericResponse.success(bookService.deleteBook(book),"Book Deleted ", HttpStatus.GONE,HttpStatus.GONE.value()));
    }
    @PutMapping("update/{id}") //done by librarian
    public ResponseEntity<GenericResponse<BookResponseDto>>updateBooks(@PathVariable long id, @RequestBody BookRequestDto book){
      return   ResponseEntity.status(HttpStatus.ACCEPTED)
              .header("update_header","update")
              .body(GenericResponse.success(bookService.updateBook(id, book),"Book Updated done",HttpStatus.ACCEPTED,HttpStatus.ACCEPTED.value()));
    }
    @GetMapping("/findBooksByAuthor") //done by both
    public GenericResponse<List<BookResponseDto>> findBooksByAuthor(@RequestParam String author){
//        return ResponseEntity.ok().body(bookService.findBookByAuthor(author));
        return GenericResponse.<List<BookResponseDto>>builder()
                .message("Getting all Books By author")
                .updatedTime(LocalTime.now())
                .httpStatus(HttpStatus.OK)
                .StatusCode(HttpStatus.OK.value())
                .status(true)
                .data(bookService.findBookByAuthor(author))
                .build();
    }
    @GetMapping("/findBooksByTitle") //done by both
    public GenericResponse<List<BookResponseDto>> findBooksByTitle(@RequestParam String title){
//        return ResponseEntity.ok().body(bookService.findBookByTitle(title));
        return GenericResponse.<List<BookResponseDto>>builder()
                .message("Getting all Books By title")
                .updatedTime(LocalTime.now())
                .httpStatus(HttpStatus.OK)
                .StatusCode(HttpStatus.OK.value())
                .status(true)
                .data(bookService.findBookByAuthor(title))
                .build();
    }
    @GetMapping("/findBooksByIsbn") // done by both
    public GenericResponse<List<BookResponseDto>> findBooksByIsbn(@RequestParam String isbn){
//        return ResponseEntity.ok().body(bookService.findBookByIsbn(isbn));
        return GenericResponse.<List<BookResponseDto>>builder()
                .message("Getting all Books By ISBN")
                .updatedTime(LocalTime.now())
                .httpStatus(HttpStatus.OK)
                .StatusCode(HttpStatus.OK.value())
                .status(true)
                .data(bookService.findBookByAuthor(isbn))
                .build();
    }

}
