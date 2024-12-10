package com.example.Entries_Project.dto.auth;

import lombok.Data;

@Data
public class AuthRegisterRequest {
    private String name;
    private String email;
    private String password;
    private String role;
}
