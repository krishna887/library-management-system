package com.example.library_management.service;

import com.example.library_management.dto.UserDto;
import com.example.library_management.dto.UserEditDto;
import com.example.library_management.dto.UserResponseDto;
import com.example.library_management.entity.Role;
import com.example.library_management.entity.User;
import com.example.library_management.exception.AlreadyExistException;
import com.example.library_management.exception.NotLoggedInUserException;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.FileAlreadyExistsException;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final ModelMapper modelMapper;
    private final  CheckLoginService checkLoginService;
    private  final PasswordGenerator passwordGenerator;

    public UserResponseDto registerStudent(UserDto userDto) {
        if ( userRepository.existsByUsername(userDto.getUsername())) {
            throw  new AlreadyExistException("User of this username Already exist");
        }
        User user = new User();
        String password = passwordGenerator.generateRandomPassword(9);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.STUDENT);
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setContactDetails(userDto.getContactDetails());
        User savedUser = userRepository.save(user);
        mailService.sendCredentialsEmail(user.getEmail(), userDto.getUsername(), password);
        return modelMapper.map(savedUser, UserResponseDto.class);

    }

    public UserResponseDto updateUser(UserEditDto updatedUser) {
// user should have logged in first
        if( !Objects.equals(checkLoginService.getCurrentAuthenticatedUsername(), updatedUser.getUsername())){
            throw new NotLoggedInUserException("User Can Update Owns details Only" );

        }
        User user = userRepository.findByUsername(updatedUser.getUsername()).map(usr -> {
            usr.setEmail(updatedUser.getEmail());
            usr.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            usr.setName(updatedUser.getName());
            usr.setContactDetails(updatedUser.getContactDetails());
            return userRepository.save(usr);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public UserResponseDto getUserByName(String username) {
      User user =userRepository.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("User of this Name not found"));
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public UserResponseDto getUserById(long id) {
        User user =userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User of this Id not found"));
        return modelMapper.map(user, UserResponseDto.class);
    }


}
