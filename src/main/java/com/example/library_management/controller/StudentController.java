package com.example.library_management.controller;

import com.example.library_management.dto.AuthResponseDto;
import com.example.library_management.dto.LoginDto;
import com.example.library_management.dto.UserEditDto;
import com.example.library_management.dto.UserResponseDto;
import com.example.library_management.entity.User;
import com.example.library_management.response.GenericResponse;
import com.example.library_management.service.AuthService;
import com.example.library_management.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final AuthService authService;
    private final UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<GenericResponse<AuthResponseDto>> studentLogin( @Valid @RequestBody LoginDto loginDto) {

        AuthResponseDto authResponseDto = authService.authenticateUser(loginDto);
        return ResponseEntity.status(HttpStatus.CREATED).header("Authentication", authResponseDto.getToken()).body(GenericResponse.empty("Student Login Success", HttpStatus.CREATED, HttpStatus.CREATED.value()));
    }

    @PutMapping("/update")
    public ResponseEntity<GenericResponse<UserResponseDto>> updateStudent(@Valid @RequestBody UserEditDto user) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("custom", "custom header").body(GenericResponse.success(userServiceImpl.updateUser(user), "Student updated Successfully", HttpStatus.ACCEPTED, HttpStatus.ACCEPTED.value()));

    }

}
