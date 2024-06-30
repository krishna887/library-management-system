package com.example.library_management.controller;

import com.example.library_management.dto.AuthResponseDto;
import com.example.library_management.dto.LoginDto;
import com.example.library_management.dto.UserDto;
import com.example.library_management.dto.UserResponseDto;
import com.example.library_management.response.GenericResponse;
import com.example.library_management.service.AuthService;
import com.example.library_management.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/librarian")
public class LibrarianController {
    //$2a$10$EvGesuRsmTihQ1K4i0WeCuVna4ya6BXqEZblury
    private final AuthService authService;
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<GenericResponse<AuthResponseDto>> login(@Valid @RequestBody LoginDto loginDto) {

        AuthResponseDto authResponseDto = authService.authenticateLibrarian(loginDto);
        return ResponseEntity.status(HttpStatus.CREATED).header("Authentication", authResponseDto.getToken()).body(GenericResponse.empty("Librarian login Success", HttpStatus.CREATED, HttpStatus.CREATED.value()));
    }

    @PostMapping("/register/student")
    public ResponseEntity<GenericResponse<UserResponseDto>> registerUser(@Valid @RequestBody UserDto user) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("custom", "custom header").body(GenericResponse.success(userService.registerStudent(user), "Student Register Successful", HttpStatus.CREATED, HttpStatus.CREATED.value()));
    }
}
