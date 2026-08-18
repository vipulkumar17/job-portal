package com.jobportal.job_portal.dto;

import com.jobportal.job_portal.entity.Application;
import lombok.Getter;
import java.time.LocalDateTime;



@Getter
public class ApplicationResponse {
    private Long id;
    private Long userId;
    private Long jobId;
    private String jobTitle;
    private String applicantName;
    private String status;
    private String resume;
    private LocalDateTime appliedAt;

    public ApplicationResponse(Application application){
        this.id=application.getId();
        this.userId=application.getUser().getId();
        this.jobId=application.getJob().getId();
        this.applicantName=application.getUser().getName();
        this.status=application.getStatus().name();
        this.resume=application.getResume();
        this.appliedAt=application.getAppliedAt();


    }


    
}
