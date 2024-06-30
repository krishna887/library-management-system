package com.example.library_management.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookRequestDto {
    @NotNull @NotEmpty
    private String title;
    @NotNull @NotEmpty
    private String author;
    @NotNull @NotEmpty
    private String isbn;
    @NotNull @NotEmpty
    private boolean isAvailable;
    @NotNull @NotEmpty
    private int copiesAvailable;
}
