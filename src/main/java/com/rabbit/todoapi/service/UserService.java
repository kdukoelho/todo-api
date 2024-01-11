package com.rabbit.todoapi.service;

import com.rabbit.todoapi.dto.UserRequestDTO;
import com.rabbit.todoapi.dto.UserResponseDTO;
import com.rabbit.todoapi.model.user.User;
import com.rabbit.todoapi.repository.UserRepository;
import com.rabbit.todoapi.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<UserResponseDTO> findAll(){
            List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
            this.userRepository.findAll().forEach(user -> userResponseDTOList.add(new UserResponseDTO(user)));
            return userResponseDTOList;
    }

    public UserResponseDTO findById(String id){
            Optional<User> user = userRepository.findById(id);
            return new UserResponseDTO(
                    user.orElseThrow(() -> new ObjectNotFoundException(
                            String.format("User id %s not found.", id))));
    }

    @Transactional
    public UserResponseDTO create(UserRequestDTO userRequestDTO){
            User user = new User(userRequestDTO);
            userRepository.save(user);
            return new UserResponseDTO(user);
    }

    @Transactional
    public UserResponseDTO update(UserRequestDTO userRequestDTO, String id){
            User user = new User(findById(id));
            user.setPasswordHash(userRequestDTO.passwordHash());
            userRepository.save(user);
            return new UserResponseDTO(user);
    }

    public String delete(String id){
            userRepository.deleteById(id);
            userRepository.deleteUserTaskRelationshipById(id);
            return "operation completed";
    }
}
