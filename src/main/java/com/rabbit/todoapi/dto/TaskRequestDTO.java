package com.rabbit.todoapi.dto;

public record TaskRequestDTO(String name, String description, String user_id) {
}
