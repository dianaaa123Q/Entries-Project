package com.example.Entries_Project.mapper.impl;

import com.example.Entries_Project.mapper.AuthMapper;
import com.example.Entries_Project.entity.User;
import com.example.Entries_Project.dto.auth.AuthRegisterRequest;
import com.example.Entries_Project.dto.auth.AuthResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthMapperImpl implements AuthMapper {
    @Override
    public AuthResponse toResponse(User user, String token) {
        AuthResponse response = new AuthResponse();
        response.setId(user.getId());
        response.setToken(token);
        return response;
    }

    @Override
    public User toUser(AuthRegisterRequest request, User user) {
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        return user;
    }
}
