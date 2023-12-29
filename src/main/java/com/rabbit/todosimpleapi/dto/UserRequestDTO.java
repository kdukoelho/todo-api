package com.rabbit.todosimpleapi.dto;

public record UserRequestDTO(String id, String username, String passwordHash) {
}
