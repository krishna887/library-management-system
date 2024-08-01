package com.example.library_management.service;

import com.example.library_management.dto.AuthResponseDto;
import com.example.library_management.dto.LoginDto;
import com.example.library_management.util.GenericResponse;


public interface AuthService {

    AuthResponseDto authenticateUser(LoginDto request);

}
