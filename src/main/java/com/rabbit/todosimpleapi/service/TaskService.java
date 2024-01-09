package com.rabbit.todosimpleapi.service;

import com.rabbit.todosimpleapi.dto.TaskRequestDTO;
import com.rabbit.todosimpleapi.dto.TaskResponseDTO;
import com.rabbit.todosimpleapi.model.Task;
import com.rabbit.todosimpleapi.repository.TaskRepository;
import com.rabbit.todosimpleapi.service.exceptions.ObjectNotFoundException;
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
    public TaskResponseDTO create(TaskRequestDTO taskRequestDTO){
            Task task = new Task(taskRequestDTO);
            taskRepository.save(task);
            return new TaskResponseDTO(task);
    }

    @Transactional
    public TaskResponseDTO update(TaskRequestDTO taskRequestDTO, String id){
            Task task = new Task(findById(id));
            task.setName(taskRequestDTO.name());
            task.setDescription(taskRequestDTO.description());
            this.taskRepository.save(task);
            return new TaskResponseDTO(task);
    }

    public String delete(String id){
            taskRepository.deleteTaskUserRelationshipById(id);
            taskRepository.deleteById(id);
            return "operation completed";
    }
}
