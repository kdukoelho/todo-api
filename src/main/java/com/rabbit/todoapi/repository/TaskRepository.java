package com.rabbit.todoapi.repository;

import com.rabbit.todoapi.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
    @Modifying
    @Query(value = "DELETE FROM user_has_task WHERE user_has_task.task_id = :task_id", nativeQuery=true)
    void deleteTaskRelationshipById(@Param("task_id") String task_id);

    @Modifying
    @Query(value="INSERT INTO user_has_task(user_id, task_id) VALUES(:user_id, :task_id)", nativeQuery = true)
    int addRelatedUser(@Param("user_id") String user_id, @Param("task_id") String task_id);

    @Query(value = "SELECT id, name, description, state, priority FROM tb_task, user_has_task WHERE tb_task.id = user_has_task.task_id AND user_has_task.user_id =  :user_id", nativeQuery = true)
    List<Task> findAllByUserId(@Param("user_id") String user_id);
}
