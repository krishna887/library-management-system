package com.example.library_management.service;

import com.example.library_management.dto.UserDto;
import com.example.library_management.dto.UserResponseDto;
import com.example.library_management.entity.Role;
import com.example.library_management.entity.User;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final ModelMapper modelMapper;

    public UserResponseDto registerStudent(UserDto userDto) {
        User user = new User();
        String password = generatePassword();
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.STUDENT);
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setContactDetails(userDto.getContactDetails());
        User savedUser = userRepository.save(user);
        mailService.sendCredentialsEmail(user.getEmail(), userDto.getUsername(), password);
        return modelMapper.map(savedUser, UserResponseDto.class);

    }

    public UserResponseDto updateUser(User updatedUser) {
        User user = userRepository.findByUsername(updatedUser.getUsername()).map(usr -> {
            usr.setEmail(updatedUser.getEmail());
            usr.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            usr.setName(updatedUser.getName());
            usr.setContactDetails(updatedUser.getContactDetails());
            return userRepository.save(usr);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return modelMapper.map(user, UserResponseDto.class);
    }

    private String generatePassword() {
        return UUID.randomUUID().toString().replace('-', '.').substring(0, 8);
    }


}
