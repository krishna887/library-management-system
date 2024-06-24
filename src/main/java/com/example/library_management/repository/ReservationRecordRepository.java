package com.example.library_management.repository;

import com.example.library_management.entity.ReservationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRecordRepository extends JpaRepository<ReservationRecord, Long> {
}
