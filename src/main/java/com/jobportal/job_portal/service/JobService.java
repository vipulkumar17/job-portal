package com.jobportal.job_portal.service;


import com.jobportal.job_portal.dto.JobRequest;
import com.jobportal.job_portal.dto.JobResponse;
import com.jobportal.job_portal.entity.Job;
import com.jobportal.job_portal.entity.User;
import com.jobportal.job_portal.repoistry.JobRepository;
import com.jobportal.job_portal.repoistry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    public JobResponse createJob(JobRequest request){
        User recruiter= userRepository.findById(request.getRecruiterId())
        .orElseThrow(()-> new RuntimeException("recruiter not found"));

        Job job=new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setSalary(request.getSalary());
        job.setLocation(request.getLocation());
        job.setRecruiter(recruiter);

        Job saved=jobRepository.save(job);
        return new JobResponse(saved);

    }

    public List<JobResponse> getAllJobs(){
        return jobRepository.findAll()
        .stream()
        .map(JobResponse::new)
        .collect(Collectors.toList());
    }
    public JobResponse getJobById(Long id ){
        Job job=jobRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Job not found"));
        return new JobResponse(job);

    }
    public JobResponse updateJob(Long id,JobRequest request){
        Job job=jobRepository.findById(id)
        .orElseThrow(()->new RuntimeException("job not found"));
        

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setSalary(request.getSalary());
        job.setLocation(request.getLocation());

        Job updated=jobRepository.save(job);
        return new JobResponse(updated);



    }
    public void deleteJob(Long Id){
        if(!jobRepository.existsById(Id)){
            throw new RuntimeException("job not found");
        }
        jobRepository.deleteById(Id);
    }



    
}
