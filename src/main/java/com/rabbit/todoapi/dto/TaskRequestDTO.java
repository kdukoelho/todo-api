package com.rabbit.todoapi.dto;

import com.rabbit.todoapi.model.task.TaskPriority;
import com.rabbit.todoapi.model.task.TaskState;

public record TaskRequestDTO(String name, String description, TaskState state, TaskPriority priority) {
}
