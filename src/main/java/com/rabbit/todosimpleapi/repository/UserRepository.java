package com.rabbit.todosimpleapi.repository;

import com.rabbit.todosimpleapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
