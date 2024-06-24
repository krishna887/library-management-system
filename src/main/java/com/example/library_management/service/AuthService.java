package com.example.library_management.service;

import com.example.library_management.exception.PasswordChangeRequiredException;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.dto.AuthenticationResponse;
import com.example.library_management.dto.LoginDto;
import com.example.library_management.entity.User;
import com.example.library_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponse authenticate(LoginDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user =userRepository.findByUsername(request.getUsername()).orElseThrow(()->new ResourceNotFoundException("librarian not found of this name"));
        String token= jwtService.generateToken(request);

        return new AuthenticationResponse(token, user.getUsername(), (List<? extends GrantedAuthority>) user.getAuthorities());

    }
    public AuthenticationResponse authenticateUser(LoginDto request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.isPasswordChangeRequired()) {
            throw new PasswordChangeRequiredException("Password change required");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        String token= jwtService.generateToken(request);

        return new AuthenticationResponse(token, user.getUsername(), (List<? extends GrantedAuthority>) user.getAuthorities());

    }
}
