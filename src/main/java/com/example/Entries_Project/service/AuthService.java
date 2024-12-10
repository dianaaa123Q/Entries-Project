package com.example.Entries_Project.service;

import com.example.Entries_Project.dto.auth.AuthLoginRequest;
import com.example.Entries_Project.dto.auth.AuthRegisterRequest;
import com.example.Entries_Project.dto.auth.AuthResponse;

public interface AuthService {
    AuthResponse register(AuthRegisterRequest request);
    AuthResponse login(AuthLoginRequest request);
}
