package com.example.Entries_Project.dto.auth;

import lombok.Data;

@Data
public class AuthLoginRequest {
    private String email;
    private String password;
}
