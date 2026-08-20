package com.jobportal.job_portal.dto;

import com.jobportal.job_portal.entity.Job;

import lombok.Getter;

@Getter
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private Double salary;
    private String location;
    private Long recruiterId;
    private String recruiterName;
  

    public JobResponse(Job job){
        this.id=job.getId();
        this.description=job.getDescription();
        this.salary=job.getSalary();
        this.title=job.getTitle();
        this.location=job.getLocation();
        this.recruiterId=job.getRecruiter().getId();
        this.recruiterName=job.getRecruiter().getName();
       
    }

    
}
