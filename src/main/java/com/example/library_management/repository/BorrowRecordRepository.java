package com.example.library_management.repository;

import com.example.library_management.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord,Long> {
}
