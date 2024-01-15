package com.rabbit.todoapi.dto;

import com.rabbit.todoapi.model.task.TaskState;

public record TaskRequestDTO(String name, String description, String user_id, TaskState state) {
}
