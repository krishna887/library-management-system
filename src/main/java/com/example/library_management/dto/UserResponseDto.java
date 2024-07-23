package com.example.library_management.dto;

import com.example.library_management.entity.BorrowRecord;
import com.example.library_management.entity.ReservationRecord;
import com.example.library_management.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {
    private  long id;
    private String username;
    private String email;
    private String name;
    private String contactDetails;
    private Role role;


}
