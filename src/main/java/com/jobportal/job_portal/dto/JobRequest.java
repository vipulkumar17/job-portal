package com.jobportal.job_portal.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JobRequest {
    private String title;
    private String description;
    private Double salary;
    private String location;
    private Long recruiterId;
    
}
