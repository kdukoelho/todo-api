package com.rabbit.todosimpleapi.dto;

import com.rabbit.todosimpleapi.model.Task;

public record TaskResponseDTO(String id, String name, String description) {
    public TaskResponseDTO(Task task){
        this(task.getId(), task.getName(), task.getDescription());
    }
}
