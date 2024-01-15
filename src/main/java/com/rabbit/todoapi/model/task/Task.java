package com.rabbit.todoapi.model.task;

import com.rabbit.todoapi.dto.TaskRequestDTO;
import com.rabbit.todoapi.dto.TaskResponseDTO;
import com.rabbit.todoapi.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = Task.TABLE_NAME)
@Data @NoArgsConstructor
public class Task {
    public static final String TABLE_NAME = "tb_task";
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private String id;

    @NotBlank
    @Column(name = "name", length = 30)
    @Size(min = 1, max = 30)
    private String name;

    @Column(name = "description", length = 100)
    @Size(max = 100)
    private String description;

    private TaskState state;

    @ManyToMany(mappedBy = "taskList")
    private List<User> usersList = new ArrayList<>();

    public Task(String id, String name, String description, TaskState state){
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
    }

    public Task(TaskRequestDTO taskRequestDTO){
        this.name = taskRequestDTO.name();
        this.description = taskRequestDTO.description();
        this.state = taskRequestDTO.state();
    }

    public Task(TaskResponseDTO taskResponseDTO){
        this.id = taskResponseDTO.id();
        this.name = taskResponseDTO.name();
        this.description = taskResponseDTO.description();
        this.state = taskResponseDTO.state();
    }
}