package com.jobportal.job_portal.dto;

import com.jobportal.job_portal.entity.Application;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusUpdateRequest {
    private Application.Status status;
    
}
