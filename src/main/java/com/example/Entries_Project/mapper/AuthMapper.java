package com.example.Entries_Project.mapper;


import com.example.Entries_Project.entity.User;
import com.example.Entries_Project.dto.auth.AuthRegisterRequest;
import com.example.Entries_Project.dto.auth.AuthResponse;

public interface AuthMapper {
    AuthResponse toResponse(User user, String token);
    User toUser(AuthRegisterRequest request, User user);
}
