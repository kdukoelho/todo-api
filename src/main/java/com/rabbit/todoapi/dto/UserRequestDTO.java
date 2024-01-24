package com.rabbit.todoapi.dto;

import com.rabbit.todoapi.model.user.UserRole;

public record UserRequestDTO(String password, UserRole role) {
}
