package com.jobportal.job_portal.controller;

import com.jobportal.job_portal.dto.*;
import com.jobportal.job_portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
       
            return ResponseEntity.ok(userService.register(request));


        
    
    
}
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request){
   
        return ResponseEntity.ok(userService.login(request));
    
}
}

