package com.example.library_management.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserEditDto {
    @NotNull @NotEmpty
    private String username;
    @NotNull @NotEmpty
    private String password;
    @NotNull @NotEmpty
    private String email;
    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty
    private String contactDetails;

}
