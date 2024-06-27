package com.example.library_management.service;

import com.example.library_management.dto.AuthResponseDto;
import com.example.library_management.dto.LoginDto;


public interface AuthService {
    AuthResponseDto authenticateLibrarian(LoginDto request);

    AuthResponseDto authenticateUser(LoginDto request);

}
