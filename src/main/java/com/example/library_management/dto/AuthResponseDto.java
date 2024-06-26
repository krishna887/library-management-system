package com.example.library_management.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String tokenType= "Bearer ";
    private String  token ;
    public AuthResponseDto(String token){
        this.token=token;
    }
}