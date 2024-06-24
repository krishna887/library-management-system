package com.example.library_management.service;

import com.example.library_management.dto.BookRequestDto;
import com.example.library_management.dto.BookResponseDto;
import com.example.library_management.entity.Book;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;


    public BookResponseDto createBook(BookRequestDto book) {
      Book bookForDb=  modelMapper.map(book,Book.class);
        Book createdBook= bookRepository.save(bookForDb);
        return modelMapper.map(createdBook, BookResponseDto.class);

    }

    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }


    public BookResponseDto updateBook(long id, BookRequestDto book) {
        Book bookFromDb= bookRepository.findById(id).map(book1 -> {
            book1.setTitle(book.getTitle());
            book1.setAuthor(book.getAuthor());
            book1.setIsbn(book.getIsbn());
            book1.setCopiesAvailable(book.getCopiesAvailable());
            return bookRepository.save(book1);
        }).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
       return modelMapper.map(bookFromDb, BookResponseDto.class);
    }
   public List<BookResponseDto> findBookByTitle(String title){

        List<Book> bookList= bookRepository.findByTitleContaining(title);
     return    bookList.stream().map(book->modelMapper.map(book, BookResponseDto.class)).collect(Collectors.toList());
    }
    public List<BookResponseDto> findBookByAuthor(String author){
        List<Book> bookList= bookRepository.findByAuthorContaining(author);
           return    bookList.stream().map(book->modelMapper.map(book, BookResponseDto.class)).collect(Collectors.toList());

    }
    public List<BookResponseDto> findBookByIsbn(String isbn){
       List<Book> bookList=         bookRepository.findByIsbn(isbn);
        return    bookList.stream().map(book->modelMapper.map(book, BookResponseDto.class)).collect(Collectors.toList());

    }
}
