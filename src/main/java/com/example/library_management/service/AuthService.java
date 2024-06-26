package com.example.library_management.service;

import com.example.library_management.dto.AuthResponseDto;
import com.example.library_management.dto.LoginDto;
import org.springframework.http.ResponseEntity;


public interface AuthService {
     AuthResponseDto authenticateLibrarian(LoginDto request);
    AuthResponseDto authenticateUser(LoginDto request);

}
