package com.jobportal.job_portal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationRequest {
    private Long userId;
    private Long jobId;
    private String resume;
    
}
