package com.example.library_management.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
    @NotNull @NotEmpty
    private String email;
    @NotNull @NotEmpty
    private String username;
    @NotNull @NotEmpty
    private String contactDetails;
}
