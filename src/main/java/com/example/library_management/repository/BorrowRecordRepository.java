package com.example.library_management.repository;

import com.example.library_management.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord,Long> {
    List<BorrowRecord> findByUserId(Long userId);
    List<BorrowRecord> findByBookIdAndReturnDateIsNull(Long bookId);
    List<BorrowRecord> findByUserIdAndFineAmountGreaterThan(Long userId, double fineAmount);
    List<BorrowRecord> findByUserIdAndFinePaidFalse(Long userId);
}
