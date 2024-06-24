package com.example.library_management.service;

import com.example.library_management.dto.UserDto;
import com.example.library_management.entity.Role;
import com.example.library_management.entity.User;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
private final MailService mailService;

public User registerStudent(UserDto userDto){
    User user= new User();
    String password= generatePassword();
    user.setPassword(passwordEncoder.encode(password));
    user.setRole(Role.STUDENT);
    user.setPasswordChangeRequired(true);
    user.setUsername(userDto.getUsername());
    user.setEmail(userDto.getEmail());
    user.setContactDetails(userDto.getContactDetails());
    User savedUser= userRepository.save(user);
    mailService.sendCredentialsEmail(user.getEmail(), userDto.getUsername(), user.getPassword());
return savedUser;

}
    public User updateUser(User updatedUser) {
        return userRepository.findByUsername(updatedUser.getUsername()).map(user -> {
            user.setEmail(updatedUser.getEmail());
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            user.setName(updatedUser.getName());
            user.setContactDetails(updatedUser.getContactDetails());
            user.setPasswordChangeRequired(false); // Password has been changed, set the flag to false
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    private String generatePassword() {
    return UUID.randomUUID().toString().replace('-','.').substring(0,8);
    }


}
