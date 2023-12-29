package com.rabbit.todosimpleapi.repository;

import com.rabbit.todosimpleapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {
}
