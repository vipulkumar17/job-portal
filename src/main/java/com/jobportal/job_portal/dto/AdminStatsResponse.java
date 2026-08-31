package com.jobportal.job_portal.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class AdminStatsResponse{
    private long totalUsers;
    private long totalRecruiters;
    private long totalJobSeekers;
    private long totalJobs;
    private long totalApplications;

    private Map<String,Long>applicationByStatus;

}