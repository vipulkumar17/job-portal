package com.jobportal.job_portal.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.job_portal.dto.AdminStatsResponse;
import com.jobportal.job_portal.entity.Application;
import com.jobportal.job_portal.entity.User;
import com.jobportal.job_portal.repoistry.ApplicationRepository;
import com.jobportal.job_portal.repoistry.JobRepository;
import com.jobportal.job_portal.repoistry.UserRepository;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    public AdminStatsResponse getStats() {
        long totalUsers = userRepository.count();
        long totalRecruiters = userRepository.countByRole(User.Role.RECRUITER);
        long totalJobSeekers = userRepository.countByRole(User.Role.USER);
        long totalJobs = jobRepository.count();
        long totalApplications = applicationRepository.count();

        Map<String, Long> applicationsByStatus = new HashMap<>();
        for (Application.Status status : Application.Status.values()) {
            applicationsByStatus.put(status.name(), applicationRepository.countByStatus(status));
        }

        return new AdminStatsResponse(
            totalUsers,
            totalRecruiters,
            totalJobSeekers ,
            totalJobs ,
            totalApplications,
            applicationsByStatus


        
        );
    }
}