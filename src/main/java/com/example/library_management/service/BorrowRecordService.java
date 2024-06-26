package com.example.library_management.service;

import com.example.library_management.entity.BorrowRecord;

import java.time.LocalDate;
import java.util.List;

public interface BorrowRecordService {

   BorrowRecord borrow(long userId, long bookId);
    BorrowRecord returnBook(long userId, long bookId);
   boolean checkAvailable(long bookId);

  List< BorrowRecord > viewBorrowRecords(long userId);
     double getTotalFines(Long userId);
     BorrowRecord payFine(Long borrowRecordId);
     double calculateFine(LocalDate borrowDate, LocalDate returnDate);
     List<BorrowRecord> getUserFines(Long userId);

}

