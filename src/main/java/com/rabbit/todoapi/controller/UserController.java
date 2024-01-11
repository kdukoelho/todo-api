package com.rabbit.todoapi.controller;

import com.rabbit.todoapi.dto.UserRequestDTO;
import com.rabbit.todoapi.dto.UserResponseDTO;
import com.rabbit.todoapi.model.user.User;
import com.rabbit.todoapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll(){
            List<UserResponseDTO> userResponseDTOList = this.userService.findAll();
            return ResponseEntity.ok().body(userResponseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable String id){
            UserResponseDTO userResponseDTO = this.userService.findById(id);
            return ResponseEntity.ok().body(userResponseDTO);
    }

    @PostMapping()
    @Validated(User.CreateUser.class)
    public ResponseEntity<Void> create(@Valid @RequestBody UserRequestDTO userRequestDTO){
            UserResponseDTO userResponseDTO = this.userService.create(userRequestDTO);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userResponseDTO.id()).toUri();
            return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(User.UpdateUser.class)
    public ResponseEntity<Void> update(@Valid @RequestBody UserRequestDTO userRequestDTO, @PathVariable String id){
            this.userService.update(userRequestDTO, id);
            return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
            this.userService.delete(id);
            return ResponseEntity.noContent().build();
    }
}
