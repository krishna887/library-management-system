package com.example.library_management.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowRecordDto {


    private long userId;
    private long bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean isReturned;
    private double fineAmount;
    private boolean finePaid;
}
