package com.example.library_management.dto;

import lombok.Data;

@Data
public class BookResponseDto {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    private int copiesAvailable;
}
