package com.rabbit.todoapi.dto;

public record UserRequestDTO(String username, String passwordHash) {
}
