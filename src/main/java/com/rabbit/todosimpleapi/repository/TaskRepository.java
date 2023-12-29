package com.rabbit.todosimpleapi.repository;

import com.rabbit.todosimpleapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, String> {
    @Query(value = "DELETE FROM user_has_task WHERE task_id = :task_id", nativeQuery=true)
    void deleteTaskUserRelationshipById(@Param("task_id") String task_id);
}
