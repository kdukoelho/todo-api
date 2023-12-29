package com.rabbit.todosimpleapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity @Table(name = User.TABLE_NAME)
@Data @NoArgsConstructor
public class User{
    public final static String TABLE_NAME = "tb_user";
    public interface CreateUser {}
    public interface UpdateUser {}

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private String id;

    @NotBlank(groups = CreateUser.class)
    @Column(name = "username",length = 60, nullable = false, unique = true)
    @Size(min = 3, max = 60)
    private String username;

    @NotBlank(groups = {CreateUser.class, UpdateUser.class})
    @Column(name = "pass_hash", length = 60, nullable = false)
    @Size(min = 8, max = 60)
    private String passwordHash;

    @ManyToMany()
    @JoinTable(name = "user_has_task",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Task> taskList = new ArrayList<Task>();

    public User(String id, String username, String passwordHash){
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
    }
}
