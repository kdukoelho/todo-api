package com.rabbit.todoapi.model.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaskState {
    TODO("todo"),
    DOING("doing"),
    DONE("done");

    private final String state;
}
