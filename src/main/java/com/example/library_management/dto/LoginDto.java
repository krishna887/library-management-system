package com.example.library_management.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotNull(message = "UserName Should not be Null")
    @NotEmpty(message = "UserName Should not be Empty")
    private String username;
    @NotNull(message = "Password Should not be Null")
    @NotEmpty(message = "Password Should not be Empty")
    private String password;
}
