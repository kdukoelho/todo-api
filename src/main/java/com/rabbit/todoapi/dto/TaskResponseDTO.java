package com.rabbit.todoapi.dto;

import com.rabbit.todoapi.model.Task;

public record TaskResponseDTO(String id, String name, String description) {
    public TaskResponseDTO(Task task){
        this(task.getId(), task.getName(), task.getDescription());
    }
}
