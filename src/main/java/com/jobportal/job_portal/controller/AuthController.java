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
        try{
            return ResponseEntity.ok(userService.register(request));


        
    }catch (RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    
}
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request){
    try{
        return ResponseEntity.ok(userService.login(request));
    }catch (RuntimeException e){
        return ResponseEntity.status(401).body(e.getMessage());

}
}
}

