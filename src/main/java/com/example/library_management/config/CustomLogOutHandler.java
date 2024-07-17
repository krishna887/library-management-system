package com.example.library_management.config;

import com.example.library_management.entity.Token;
import com.example.library_management.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomLogOutHandler implements LogoutHandler {
    private final TokenRepository tokenRepository;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        String token = authHeader.substring(7);
// get the token from db
        Token storedToken = tokenRepository.findByToken(token).orElse(null);
        // invalidate the token by making log out true
        if (storedToken != null) {
            storedToken.set_logged_out(true);
            // save the token
            tokenRepository.save(storedToken);
        }
    }
}
