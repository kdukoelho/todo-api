package com.rabbit.todoapi.repository;

import com.rabbit.todoapi.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "DELETE FROM user_has_task WHERE user_id = :user_id;", nativeQuery = true)
    void deleteUserTaskRelationshipById(@Param("user_id") String user_id);

    UserDetails findByUsername(String username);

}
