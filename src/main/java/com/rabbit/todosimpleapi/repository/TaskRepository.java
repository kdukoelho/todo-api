package com.rabbit.todosimpleapi.repository;

import com.rabbit.todosimpleapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
    @Query(value = "DELETE FROM user_has_task WHERE task_id = :task_id;", nativeQuery=true)
    void deleteTaskUserRelationshipById(@Param("task_id") String task_id);

    @Query(value = "SELECT id, name, description FROM tb_task, user_has_task WHERE tb_task.id = user_has_task.task_id AND user_has_task.user_id =  :user_id", nativeQuery = true)
    List<Task> findAllByUserId(@Param("user_id") String user_id);
}
