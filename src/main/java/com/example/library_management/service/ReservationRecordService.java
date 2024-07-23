package com.example.library_management.service;

import com.example.library_management.dto.ReservationRecordDto;

import java.util.List;

public interface ReservationRecordService {
    ReservationRecordDto reserveBook(Long userId, Long bookId);

    String cancelReservation(Long reservationId);

    List<ReservationRecordDto> viewReservationsByUser(Long userId);

    List<ReservationRecordDto> getAllReservations();
}
