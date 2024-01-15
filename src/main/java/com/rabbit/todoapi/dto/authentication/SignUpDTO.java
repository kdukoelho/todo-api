package com.rabbit.todoapi.dto.authentication;

import com.rabbit.todoapi.model.user.UserRole;

public record SignUpDTO(String username, String password, UserRole role) {
}
