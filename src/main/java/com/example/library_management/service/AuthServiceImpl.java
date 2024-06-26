package com.example.library_management.service;

import com.example.library_management.dto.AuthResponseDto;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.dto.LoginDto;
import com.example.library_management.entity.User;
import com.example.library_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public AuthResponseDto authenticateLibrarian(LoginDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        String token= jwtService.generateToken(request.getUsername());
         return new AuthResponseDto(token);
    }
    public AuthResponseDto authenticateUser(LoginDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        String token= jwtService.generateToken(request.getUsername());

return new AuthResponseDto(token);

    }
}
