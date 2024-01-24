package com.rabbit.todoapi.model.user;

import com.rabbit.todoapi.dto.UserRequestDTO;
import com.rabbit.todoapi.dto.UserResponseDTO;
import com.rabbit.todoapi.model.task.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity @Table(name = User.TABLE_NAME)
@Data @NoArgsConstructor
public class User implements UserDetails {
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

    private UserRole role;

    public User(String username, String passwordHash, UserRole role){
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public User(UserRequestDTO userRequestDTO){
        this.passwordHash = userRequestDTO.password();
        this.role = userRequestDTO.role();
    }

    public User(UserResponseDTO userResponseDTO){
        this.id = userResponseDTO.id();
        this.username = userResponseDTO.username();
        this.passwordHash = userResponseDTO.passwordHash();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return this.passwordHash;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
