package com.example.library_management.controller;

import com.example.library_management.dto.AuthenticationResponse;
import com.example.library_management.dto.LoginDto;
import com.example.library_management.dto.UserDto;
import com.example.library_management.entity.User;
import com.example.library_management.service.AuthService;
import com.example.library_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/")
public class LibraryController {
    private final AuthService authService;
    private final UserService userService;
private final PasswordEncoder encoder;
    @PostMapping("login/librarian")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.authenticate(loginDto));
    }
    @PostMapping("register/student")
    public ResponseEntity<User> registerUser(@RequestBody UserDto user){
        return ResponseEntity.ok().body(userService.registerStudent(user));
    }
    @PutMapping("update/student")
    public ResponseEntity<User> updateStudent(@RequestBody User user){
        return ResponseEntity.ok().body(userService.updateUser(user));
    }

    @PostMapping("login/student")
    public ResponseEntity<AuthenticationResponse> studentLogin(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.authenticateUser(loginDto));
    }

}
