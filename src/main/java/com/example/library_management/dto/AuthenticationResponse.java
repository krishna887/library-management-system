package com.example.library_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationResponse {
    private  String token;
    private String userName;
    private String message;
    private List<? extends GrantedAuthority> roles;
}