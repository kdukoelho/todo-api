package com.rabbit.todoapi.controller;

import com.rabbit.todoapi.dto.authentication.LoginResponseDTO;
import com.rabbit.todoapi.dto.authentication.SignInDTO;
import com.rabbit.todoapi.dto.authentication.SignUpDTO;
import com.rabbit.todoapi.model.user.User;
import com.rabbit.todoapi.repository.UserRepository;
import com.rabbit.todoapi.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid SignInDTO signInDTO){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(signInDTO.username(), signInDTO.password());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid SignUpDTO signUpDTO){
        if (this.userRepository.findByUsername(signUpDTO.username()) != null) { return ResponseEntity.badRequest().build(); }

        String passwordHash = new BCryptPasswordEncoder().encode(signUpDTO.password());

        this.userRepository.save(new User(signUpDTO.username(), passwordHash, signUpDTO.role()));

        return ResponseEntity.ok().build();
    }

}
