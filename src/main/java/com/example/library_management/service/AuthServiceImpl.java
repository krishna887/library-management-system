package com.example.library_management.service;

import com.example.library_management.dto.AuthResponseDto;
import com.example.library_management.dto.LoginDto;
import com.example.library_management.entity.Token;
import com.example.library_management.entity.User;
import com.example.library_management.repository.TokenRepository;
import com.example.library_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private  final UserRepository userRepository;
    private final TokenRepository tokenRepository;


    public AuthResponseDto authenticateLibrarian(LoginDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String token = jwtService.generateToken(request.getUsername());
        User user= userRepository.findByUsername(request.getUsername()).get();
        revokeAllToken(user);
        saveToken(user, token);
        return new AuthResponseDto(token);
    }

    private void revokeAllToken(User user) {
        List<Token> validateTokenlist= tokenRepository.findAllTokenByUser(user.getId());
        if(!validateTokenlist.isEmpty()){
            validateTokenlist.forEach(t->t.set_logged_out(true));
        }
        tokenRepository.saveAll(validateTokenlist);
    }

    private void saveToken(User user, String token) {
        Token token1= new Token();
        token1.setToken(token);
        token1.set_logged_out(false);
        token1.setUser(user);
        tokenRepository.save(token1);

    }

    public AuthResponseDto authenticateUser(LoginDto request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String token = jwtService.generateToken(request.getUsername());
        User user= userRepository.findByUsername(request.getUsername()).get();
        revokeAllToken(user);
        saveToken(user,token);
        return new AuthResponseDto(token);

    }
}
