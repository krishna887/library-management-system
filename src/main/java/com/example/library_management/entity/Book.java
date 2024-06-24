package com.example.library_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    @OneToMany(mappedBy = "book")
private  List<BorrowRecord> borrowRecords;

    @OneToMany(mappedBy = "book")
    private List<ReservationRecord> reservations;


}

