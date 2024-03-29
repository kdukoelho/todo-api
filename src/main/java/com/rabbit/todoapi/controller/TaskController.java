package com.rabbit.todoapi.controller;

import com.rabbit.todoapi.dto.TaskRequestDTO;
import com.rabbit.todoapi.dto.TaskResponseDTO;
import com.rabbit.todoapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskResponseDTO>> findByUserId(@PathVariable String userId){
        try{
            List<TaskResponseDTO> taskResponseDTOList = this.taskService.findAllByUserId(userId);
            return ResponseEntity.ok().body(taskResponseDTOList);
        } catch(RuntimeException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> findAll() {
            List<TaskResponseDTO> taskResponseDTOList = this.taskService.findAll();
            return ResponseEntity.ok().body(taskResponseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findById(@PathVariable String id) {
            TaskResponseDTO responseDTO = this.taskService.findById(id);
            return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Void> create(@RequestBody TaskRequestDTO taskRequestDTO, @PathVariable String userId) {
            TaskResponseDTO taskResponseDTO = this.taskService.create(taskRequestDTO, userId);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(taskResponseDTO.id()).toUri();
            return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody TaskRequestDTO taskRequestDTO, @PathVariable String id) {
            this.taskService.update(taskRequestDTO, id);
            return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{task_id}")
    public ResponseEntity<Void> delete(@PathVariable String task_id){
            this.taskService.delete(task_id);
            return ResponseEntity.noContent().build();
    }
}
