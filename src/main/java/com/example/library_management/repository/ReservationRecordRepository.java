package com.example.library_management.repository;

import com.example.library_management.entity.ReservationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRecordRepository extends JpaRepository<ReservationRecord, Long> {
    List<ReservationRecord> findByUserId(Long userId);
    List<ReservationRecord> findByBookIdAndActiveTrue(Long bookId);
}
