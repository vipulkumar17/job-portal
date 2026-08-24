package com.jobportal.job_portal.dto;

import com.jobportal.job_portal.entity.User;

import lombok.Getter;

@Getter
public class AuthResponse {
    private Long id;
    private String name;
    private String email;
    private User.Role role;
    private String token;

    public AuthResponse(User user){
        this.id= user.getId();
        this.name=user.getName();
        this.email=user.getEmail();
        this.role=user.getRole();
    }
    public AuthResponse(User user , String token){
        this(user);
        this.token=token;
    }
    
}
