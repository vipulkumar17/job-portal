package com.jobportal.job_portal.service;

import com.jobportal.job_portal.dto.*;
import com.jobportal.job_portal.entity.Application;
import com.jobportal.job_portal.entity.User;
import com.jobportal.job_portal.exception.DuplicateResourceException;
import com.jobportal.job_portal.exception.ResourceNotFoundException;
import com.jobportal.job_portal.entity.Job;
import com.jobportal.job_portal.repoistry.ApplicationRepository;
import com.jobportal.job_portal.repoistry.UserRepository;
import com.jobportal.job_portal.repoistry.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;


    public ApplicationResponse uploadResume(Long applicationId,MultipartFile file ){
        Application application=applicationRepository.findById(applicationId)
        .orElseThrow(()-> new ResourceNotFoundException("application not found")); 

        String storedFilename=fileStorageService.storeFile(file);

        application.setResume(storedFilename);
        Application updated=applicationRepository.save(application);
        return new ApplicationResponse(updated);
    } 

    public ApplicationResponse apply(ApplicationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        applicationRepository.findByUserIdAndJobId(request.getUserId(), request.getJobId())
                .ifPresent(existing -> {
                    throw new DuplicateResourceException("You have already applied to this job");
                });

        Application application = new Application();
        application.setUser(user);
        application.setJob(job);
        application.setResume(request.getResume());

        Application saved = applicationRepository.save(application);
        return new ApplicationResponse(saved);
    }

    public List<ApplicationResponse> getApplicationByUserId(Long userId) {
        return applicationRepository.findByUserId(userId)
                .stream()
                .map(ApplicationResponse::new)
                .collect(Collectors.toList());
    }

    public List<ApplicationResponse> getApplicationByJobId(Long jobId) {
        return applicationRepository.findByJobId(jobId)
                .stream()
                .map(ApplicationResponse::new)
                .collect(Collectors.toList());
    }

    public ApplicationResponse updateStatus(Long id, StatusUpdateRequest request) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        application.setStatus(request.getStatus());
        Application updated = applicationRepository.save(application);
        return new ApplicationResponse(updated);
    }
}

    

