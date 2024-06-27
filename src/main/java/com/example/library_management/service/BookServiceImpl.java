package com.example.library_management.service;

import com.example.library_management.dto.BookRequestDto;
import com.example.library_management.dto.BookResponseDto;
import com.example.library_management.entity.Book;
import com.example.library_management.exception.CustomIllegalStateException;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;


    public BookResponseDto createBook(BookRequestDto dto) {
        Book existingBook = bookRepository.findByTitleAndAuthorAndIsbn(dto.getTitle(), dto.getAuthor(), dto.getIsbn());
        if (existingBook != null && existingBook.isAvailable()) {
            existingBook.setCopiesAvailable(existingBook.getCopiesAvailable() + dto.getCopiesAvailable());
            existingBook.setAvailable(true);
            Book bookInDb = bookRepository.save(existingBook);
            return modelMapper.map(bookInDb, BookResponseDto.class);
        } else {
            Book book = new Book();
            book.setTitle(dto.getTitle());
            book.setAuthor(dto.getAuthor());
            book.setIsbn(dto.getIsbn());
            book.setAvailable(dto.getCopiesAvailable() > 0);
            book.setCopiesAvailable(dto.getCopiesAvailable());
            Book bookInDb = bookRepository.save(book);
            return modelMapper.map(bookInDb, BookResponseDto.class);
        }
    }

    public BookResponseDto deleteBook(BookRequestDto dto) {
        Book existingBook = bookRepository.findByTitleAndAuthorAndIsbn(dto.getTitle(), dto.getAuthor(), dto.getIsbn());
        if (existingBook != null && existingBook.isAvailable()) {
            if (existingBook.getCopiesAvailable() < dto.getCopiesAvailable()) {
                throw new CustomIllegalStateException("There is no enough books to delete");
            }
            existingBook.setCopiesAvailable(existingBook.getCopiesAvailable() - dto.getCopiesAvailable());
            if (existingBook.getCopiesAvailable() == 0) {
                existingBook.setAvailable(false);
                Book bookInDb = bookRepository.save(existingBook);
                return modelMapper.map(bookInDb, BookResponseDto.class);
            }
            Book bookInDb = bookRepository.save(existingBook); //save remaining book
            return modelMapper.map(bookInDb, BookResponseDto.class);

        }
        throw new ResourceNotFoundException("Book not found for Deletion");
    }


    public BookResponseDto updateBook(long id, BookRequestDto dto) {
        Book bookFromDb = bookRepository.findById(id).map(book1 -> {
            book1.setTitle(dto.getTitle());
            book1.setAuthor(dto.getAuthor());
            book1.setIsbn(dto.getIsbn());
            book1.setAvailable(dto.isAvailable());
            book1.setCopiesAvailable(dto.getCopiesAvailable());
            return bookRepository.save(book1);
        }).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return modelMapper.map(bookFromDb, BookResponseDto.class);
    }

    public List<BookResponseDto> findBookByTitle(String title) {

        List<Book> bookList = bookRepository.findByTitleContaining(title);
        return bookList.stream().map(book -> modelMapper.map(book, BookResponseDto.class)).collect(Collectors.toList());
    }

    public List<BookResponseDto> findBookByAuthor(String author) {
        List<Book> bookList = bookRepository.findByAuthorContaining(author);
        return bookList.stream().map(book -> modelMapper.map(book, BookResponseDto.class)).collect(Collectors.toList());

    }

    public List<BookResponseDto> findBookByIsbn(String isbn) {
        List<Book> bookList = bookRepository.findByIsbn(isbn);
        return bookList.stream().map(book -> modelMapper.map(book, BookResponseDto.class)).collect(Collectors.toList());

    }
}
