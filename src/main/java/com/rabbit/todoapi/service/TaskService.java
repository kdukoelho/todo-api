package com.rabbit.todoapi.service;

import com.rabbit.todoapi.dto.TaskRequestDTO;
import com.rabbit.todoapi.dto.TaskResponseDTO;
import com.rabbit.todoapi.model.task.Task;
import com.rabbit.todoapi.repository.TaskRepository;
import com.rabbit.todoapi.service.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskResponseDTO> findAllByUserId(String userId){
            ArrayList<TaskResponseDTO> taskResponseDTOList = new ArrayList<>();
            this.taskRepository.findAllByUserId(userId).forEach(task -> taskResponseDTOList.add(new TaskResponseDTO(task)));
            return taskResponseDTOList;
    }

    public List<TaskResponseDTO> findAll(){
            ArrayList<TaskResponseDTO> taskResponseDTOList = new ArrayList<>();
            this.taskRepository.findAll().forEach(task -> taskResponseDTOList.add(new TaskResponseDTO(task)));
            return taskResponseDTOList;
    }

    public TaskResponseDTO findById(String id){
            Optional<Task> task = taskRepository.findById(id);
            return new TaskResponseDTO(
                    task.orElseThrow(() -> new ObjectNotFoundException(
                            String.format("Task id %s not found.", id))));
    }

    @Transactional
    public TaskResponseDTO create(@Valid TaskRequestDTO taskRequestDTO, String userId){
            Task task = new Task(taskRequestDTO);
            taskRepository.save(task);
            taskRepository.addRelatedUser(userId, task.getId());
            return new TaskResponseDTO(task);
    }

    @Transactional
    public TaskResponseDTO update(@Valid TaskRequestDTO taskRequestDTO, String id){
            Task task = new Task(taskRequestDTO);
            task.setId(id);
            this.taskRepository.save(task);
            return new TaskResponseDTO(task);
    }

    @Transactional
    public String delete(String task_id){
            taskRepository.deleteTaskRelationshipById(task_id);
            taskRepository.deleteById(task_id);
            return "operation completed";
    }
}
