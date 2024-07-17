package com.example.library_management.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;
    private boolean is_logged_out;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
