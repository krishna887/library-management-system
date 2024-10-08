package com.example.library_management.controller;

import com.example.library_management.dto.AuthResponseDto;
import com.example.library_management.dto.LoginDto;
import com.example.library_management.dto.UserEditDto;
import com.example.library_management.dto.UserResponseDto;
import com.example.library_management.service.UserService;
import com.example.library_management.util.GenericResponse;
import com.example.library_management.service.AuthService;
import com.example.library_management.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final AuthService authService;
    private final UserService userServiceImpl;

    @PutMapping("/update")
    public ResponseEntity<GenericResponse<UserResponseDto>> updateStudent(@Valid @RequestBody UserEditDto user) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(GenericResponse.success(userServiceImpl.updateUser(user), "Student updated Successfully", HttpStatus.ACCEPTED, HttpStatus.ACCEPTED.value()));

    }
    @GetMapping("/getUserDetailsByName/{username}")
    public  ResponseEntity<GenericResponse<UserResponseDto>> getByUsername(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK)
                .body(GenericResponse.success(userServiceImpl.getUserByName(username), "Student Details Fetch Successfully", HttpStatus.OK, HttpStatus.OK.value()));

    }
    @GetMapping("/getUserDetailsById/{id}")
    public  ResponseEntity<GenericResponse<UserResponseDto>> getUserById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(GenericResponse.success(userServiceImpl.getUserById(id), "Student Details Fetch Successfully", HttpStatus.OK, HttpStatus.OK.value()));

    }

}
