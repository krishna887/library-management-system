package com.example.library_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
    @Email
    private String email;
    @NotNull(message = "UserName Should not be Null")
    @NotEmpty(message = "UserName Should not be Empty")
    private String username;
    @NotNull(message = "ContactDetails Should not be Null")
    @NotEmpty(message = "ContactDetails Should not be Empty")
    private String contactDetails;
}
