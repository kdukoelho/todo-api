package com.rabbit.todosimpleapi.dto;

import com.rabbit.todosimpleapi.model.User;

public record UserResponseDTO(String id, String username, String passwordHash) {
    public UserResponseDTO(User user){
        this(user.getId(), user.getUsername(), user.getPasswordHash());
    }
}
