package com.example.library_management.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookRequestDto {
    @NotNull(message = "Title Should not be Null")
    @NotEmpty(message = "Title Should not be Empty")
    private String title;
    @NotNull(message = "Author Should not be Null")
    @NotEmpty(message = "Author Should not be Empty")
    private String author;
    @NotNull(message = "Isbn Should not be Null")
    @NotEmpty(message = "Isbn Should not be Empty")
    private String isbn;

    private boolean isAvailable;

    private int copiesAvailable;
}
