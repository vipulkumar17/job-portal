package com.jobportal.job_portal.dto;


import com.jobportal.job_portal.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private User.Role role;
    
}
