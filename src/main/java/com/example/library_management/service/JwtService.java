package com.example.library_management.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    boolean isValid(String token, UserDetails user);
    String extractUsername(String token);
    String generateToken(String username);
}
