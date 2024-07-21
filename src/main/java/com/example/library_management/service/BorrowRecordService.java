package com.example.library_management.service;

import com.example.library_management.dto.BorrowRecordDto;

import java.util.List;

public interface BorrowRecordService {

    BorrowRecordDto borrow(long userId, long bookId);

    BorrowRecordDto returnBook(long userId, long bookId);

    boolean checkAvailable(long bookId);

    List<BorrowRecordDto> viewBorrowRecords(long userId);

    Double getTotalFines(Long userId);

    BorrowRecordDto payFine(Long borrowRecordId);

    Double calculateFine(long borrowId);

    List<BorrowRecordDto> getUserFines(Long userId);

    List<BorrowRecordDto> viewAllBorrowRecords();
}

