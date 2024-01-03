package com.rabbit.todosimpleapi.service;

import com.rabbit.todosimpleapi.dto.UserRequestDTO;
import com.rabbit.todosimpleapi.dto.UserResponseDTO;
import com.rabbit.todosimpleapi.model.User;
import com.rabbit.todosimpleapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO findById(String id){
        try{
            Optional<User> user = userRepository.findById(id);
            return new UserResponseDTO(
                    user.orElseThrow(() -> new RuntimeException(
                            String.format("User id %s not found.", id))));
        } catch(RuntimeException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Transactional
    public UserResponseDTO create(UserRequestDTO userRequestDTO){
        try{
            User user = new User(userRequestDTO);
            userRepository.save(user);
            return new UserResponseDTO(user);
        } catch(RuntimeException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Transactional
    public UserResponseDTO update(UserRequestDTO userRequestDTO, String id){
        try{
            User user = new User(findById(id));
            user.setPasswordHash(userRequestDTO.passwordHash());
            userRepository.save(user);
            return new UserResponseDTO(user);
        } catch(RuntimeException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public String delete(String id){
        try{
            userRepository.deleteById(id);
            userRepository.deleteUserTaskRelationshipById(id);
            return "operation completed";
        } catch(RuntimeException ex){
            System.out.println(ex.getMessage());
        }
        return "operation failed";
    }
}
