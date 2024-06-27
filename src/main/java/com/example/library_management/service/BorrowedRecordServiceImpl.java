package com.example.library_management.service;

import com.example.library_management.dto.BorrowRecordDto;
import com.example.library_management.entity.Book;
import com.example.library_management.entity.BorrowRecord;
import com.example.library_management.entity.User;
import com.example.library_management.exception.CustomIllegalStateException;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.repository.BookRepository;
import com.example.library_management.repository.BorrowRecordRepository;
import com.example.library_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowedRecordServiceImpl implements BorrowRecordService {
    private static final double FINE_PER_DAY = 200;
    private final BorrowRecordRepository borrowRecordRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ModelMapper mapper;

    @Override
    public BorrowRecordDto borrow(long userId, long bookId) {
        if (getTotalFines(userId) >= 500) {
            throw new IllegalStateException("Cannot borrow books with fines >= 500");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User of This id is not found"));
        Book book = bookRepository.findBookById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book of this Id is not found"));

        if (book.getCopiesAvailable() <= 0 && !book.isAvailable()) {
            throw new IllegalStateException("Book is not available");
        }
        book.setCopiesAvailable(book.getCopiesAvailable() - 1);
        if (book.getCopiesAvailable() == 0) {
            book.setAvailable(false);
            bookRepository.save(book);
        }
        bookRepository.save(book);

        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setUser(user);
        borrowRecord.setBook(book);
        borrowRecord.setBorrowDate(LocalDate.now());
        BorrowRecord borrowRecord1 = borrowRecordRepository.save(borrowRecord);
        return mapper.map(borrowRecord1, BorrowRecordDto.class);
    }

    @Override
    public BorrowRecordDto returnBook(long userId, long bookId) {
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Book book = bookRepository.findBookById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        BorrowRecord borrowRecord = borrowRecordRepository.findByBookIdAndReturnDateIsNull(bookId).stream().filter(record -> record.getUser().getId().equals(userId)).findFirst().orElseThrow(() -> new IllegalStateException("Borrow record not found"));

        borrowRecord.setReturnDate(LocalDate.now());
        borrowRecord.setReturned(true);
        borrowRecordRepository.save(borrowRecord);

        book.setCopiesAvailable(book.getCopiesAvailable() + 1);
        book.setAvailable(true);
        bookRepository.save(book);

        return mapper.map(borrowRecord, BorrowRecordDto.class);
    }

    @Override
    public boolean checkAvailable(long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book of this Id is not found"));
        return book.getCopiesAvailable() > 0;

    }

    @Override
    public List<BorrowRecordDto> viewBorrowRecords(long userId) {
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(" User of this id is not found"));
        List<BorrowRecord> borrowRecordList = borrowRecordRepository.findByUserId(userId);
        return borrowRecordList.stream().map(borrowRecord -> mapper.map(borrowRecord, BorrowRecordDto.class)).collect(Collectors.toList());
    }

    @Override
    public Double getTotalFines(Long userId) {
        return borrowRecordRepository.findByUserIdAndFinePaidFalse(userId).stream().mapToDouble(BorrowRecord::getFineAmount).sum();
    }

    @Override
    public BorrowRecordDto payFine(Long borrowRecordId) {
        BorrowRecord borrowRecord = borrowRecordRepository.findById(borrowRecordId).orElseThrow(() -> new ResourceNotFoundException("Borrow record not found"));

        if (borrowRecord.getFineAmount() <= 0) {
            throw new CustomIllegalStateException("No fine to pay");
        }

        borrowRecord.setFinePaid(true);
        borrowRecordRepository.save(borrowRecord);
        return mapper.map(borrowRecord, BorrowRecordDto.class);
    }

    @Override
    public Double calculateFine(long borrowId) {
        BorrowRecord borrowRecord = borrowRecordRepository.findById(borrowId).orElseThrow(() -> new ResourceNotFoundException("Borrow Id Not found"));
        long daysOverdue = ChronoUnit.DAYS.between(borrowRecord.getBorrowDate(), borrowRecord.getReturnDate()) - 15; // Assuming a 15-day borrowing period
        Double fine = daysOverdue > 0 ? daysOverdue * FINE_PER_DAY : 0;
        borrowRecord.setFineAmount(fine);
        borrowRecordRepository.save(borrowRecord);
        return fine;
    }

    @Override
    public List<BorrowRecordDto> getUserFines(Long userId) {
        List<BorrowRecord> borrowRecordList = borrowRecordRepository.findByUserIdAndFineAmountGreaterThan(userId, 0);
        return borrowRecordList.stream().map(borrowRecord -> mapper.map(borrowRecord, BorrowRecordDto.class)).collect(Collectors.toList());
    }
}
