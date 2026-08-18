package com.jobportal.job_portal.repoistry;

import com.jobportal.job_portal.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application,Long>{

    List<Application> findByUserId(Long userId);
    List<Application> findByJobId(Long jobId);

    Optional<Application> findByUserIdAndJobId(long userId,Long jobId);
} 