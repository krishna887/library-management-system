package com.example.library_management.service;

import com.example.library_management.entity.Book;
import com.example.library_management.entity.BorrowRecord;
import com.example.library_management.entity.User;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.repository.BookRepository;
import com.example.library_management.repository.BorrowRecordRepository;
import com.example.library_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BorrowedRecordServiceImpl implements BorrowRecordService{
    private static final double FINE_PER_DAY = 200;
    private final BorrowRecordRepository borrowRecordRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public BorrowRecord borrow(long userId, long bookId) {
        if (getTotalFines(userId) >= 500) {
            throw new IllegalStateException("Cannot borrow books with fines >= 500");
        }
        User user= userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User of This id is not found"));
        Book book= bookRepository.findById(bookId).orElseThrow(()->
                new ResourceNotFoundException("Book of this Id is not found"));

        if (book.getCopiesAvailable() <= 0) {
            throw new IllegalStateException("Book is not available");
        }
        book.setCopiesAvailable(book.getCopiesAvailable() - 1);
        bookRepository.save(book);

        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setUser(user);
        borrowRecord.setBook(book);
        borrowRecord.setBorrowDate(LocalDate.now());
        return borrowRecordRepository.save(borrowRecord);
    }

    @Override
    public BorrowRecord returnBook(long userId, long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        BorrowRecord borrowRecord = borrowRecordRepository.findByBookIdAndReturnDateIsNull(bookId)
                .stream()
                .filter(record -> record.getUser().getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Borrow record not found"));

        borrowRecord.setReturnDate(LocalDate.now());
        borrowRecordRepository.save(borrowRecord);

        book.setCopiesAvailable(book.getCopiesAvailable() + 1);
        bookRepository.save(book);

        return borrowRecord;
    }

    @Override
    public boolean checkAvailable(long bookId) {
       Book book= bookRepository.findById(bookId).orElseThrow(()->
               new ResourceNotFoundException("Book of this Id is not found"));
        return book.getCopiesAvailable() > 0;

    }
    @Override
    public List<BorrowRecord> viewBorrowRecords(long userId) {
        User user= userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException(" User of this id is not found"));
        return borrowRecordRepository.findByUserId(userId);
    }

    @Override
    public double getTotalFines(Long userId) {
        return borrowRecordRepository.findByUserIdAndFinePaidFalse(userId)
                .stream()
                .mapToDouble(BorrowRecord::getFineAmount)
                .sum();
    }

    @Override
    public BorrowRecord payFine(Long borrowRecordId) {
        BorrowRecord borrowRecord = borrowRecordRepository.findById(borrowRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow record not found"));

        if (borrowRecord.getFineAmount() <= 0) {
            throw new IllegalStateException("No fine to pay");
        }

        borrowRecord.setFinePaid(true);
        return borrowRecordRepository.save(borrowRecord);
    }

    @Override
    public double calculateFine(LocalDate borrowDate, LocalDate returnDate) {
        long daysOverdue = ChronoUnit.DAYS.between(borrowDate, returnDate) - 14; // Assuming a 14-day borrowing period
        return daysOverdue > 0 ? daysOverdue * FINE_PER_DAY : 0;
    }

    public List<BorrowRecord> getUserFines(Long userId) {
        return borrowRecordRepository.findByUserIdAndFineAmountGreaterThan(userId, 0);
    }
}
