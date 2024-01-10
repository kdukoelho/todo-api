package com.rabbit.todoapi.dto;

import com.rabbit.todoapi.model.User;

public record UserResponseDTO(String id, String username, String passwordHash) {
    public UserResponseDTO(User user){
        this(user.getId(), user.getUsername(), user.getPasswordHash());
    }
}
