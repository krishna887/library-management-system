package com.example.library_management.service;

import com.example.library_management.entity.ReservationRecord;

import java.util.List;

public interface ReservationRecordService {
     ReservationRecord reserveBook(Long userId, Long bookId);
     void cancelReservation(Long reservationId) ;
     List<ReservationRecord> viewReservationsByUser(Long userId) ;

    }
