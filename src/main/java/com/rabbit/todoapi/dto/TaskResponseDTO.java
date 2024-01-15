package com.rabbit.todoapi.dto;

import com.rabbit.todoapi.model.task.Task;
import com.rabbit.todoapi.model.task.TaskState;

public record TaskResponseDTO(String id, String name, String description, TaskState state) {
    public TaskResponseDTO(Task task){
        this(task.getId(), task.getName(), task.getDescription(), task.getState());
    }
}
