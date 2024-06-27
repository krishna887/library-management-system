package com.example.library_management.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationRecordDto {
    private long userId;
    private long bookId;
    private LocalDate reservationDate;
    private boolean isCancelled;
    private boolean isActive;
}
