package com.example.library_management.service;

import com.example.library_management.dto.UserDto;
import com.example.library_management.dto.UserResponseDto;
import com.example.library_management.entity.User;


public interface UserService {
     UserResponseDto registerStudent(UserDto userDto);
     UserResponseDto updateUser(User updatedUser);

}
