package com.jobportal.job_portal.service;

import com.jobportal.job_portal.dto.*;
import com.jobportal.job_portal.entity.User;
import com.jobportal.job_portal.exception.DuplicateResourceException;
import com.jobportal.job_portal.exception.ResourceNotFoundException;
import com.jobportal.job_portal.repoistry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public AuthResponse register(RegisterRequest request){
        if (userRepository.existsByEmail(request.getEmail())){
            throw new DuplicateResourceException("email already registered");
        }
        User user =new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        User saved=userRepository.save(user);
        return new AuthResponse(saved);
    }
    public AuthResponse login(LoginRequest request){
        User user =userRepository.findByEmail(request.getEmail())
        .orElseThrow(()->new ResourceNotFoundException("invalid email or password"));

        if(!user.getPassword().equals(request.getPassword())){
            throw new RuntimeException("invalid email or password");
        }

        return new AuthResponse(user);
        

    }
    
}
