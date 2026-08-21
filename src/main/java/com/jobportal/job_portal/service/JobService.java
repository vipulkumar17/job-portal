package com.jobportal.job_portal.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jobportal.job_portal.dto.JobRequest;
import com.jobportal.job_portal.dto.JobResponse;
import com.jobportal.job_portal.entity.Job;
import com.jobportal.job_portal.entity.User;
import com.jobportal.job_portal.exception.ResourceNotFoundException;
import com.jobportal.job_portal.repoistry.JobRepository;
import com.jobportal.job_portal.repoistry.JobSpecification;
import com.jobportal.job_portal.repoistry.UserRepository;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    public JobResponse createJob(JobRequest request){
        User recruiter= userRepository.findById(request.getRecruiterId())
        .orElseThrow(()-> new ResourceNotFoundException("recruiter not found"));

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
        .orElseThrow(()->new ResourceNotFoundException("Job not found"));
        return new JobResponse(job);

    }
    public JobResponse updateJob(Long id,JobRequest request){
        Job job=jobRepository.findById(id)
        .orElseThrow(()->new ResourceNotFoundException("job not found"));
        

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setSalary(request.getSalary());
        job.setLocation(request.getLocation());

        Job updated=jobRepository.save(job);
        return new JobResponse(updated);



    }
    public void deleteJob(Long Id){
        if(!jobRepository.existsById(Id)){
            throw new ResourceNotFoundException("job not found");
        }
        jobRepository.deleteById(Id);
    }


    public Page<JobResponse> searchJobs(String title,String location, Double minSalary, Double maxSalary, Pageable pageable){

        Specification<Job> spec=Specification
        .where(JobSpecification.hasTitle(title))
        .and(JobSpecification.hasLocation(location))
        .and(JobSpecification.salaryBetween(minSalary, maxSalary));

        Page<Job> jobPage=jobRepository.findAll(spec,pageable);

        return jobPage.map(JobResponse::new);

    }
}



    

