package com.jobportal.job_portal.controller;


import com.jobportal.job_portal.dto.JobRequest;
import com.jobportal.job_portal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;





@RestController
@RequestMapping("/api/jobs")

public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping
    public ResponseEntity<?> createJob(@RequestBody JobRequest request){
        return ResponseEntity.ok(jobService.createJob(request));
       
    }

    @GetMapping
    public ResponseEntity<?> getAllJobs(){
        return ResponseEntity.ok(jobService.getAllJobs());
    }    

    @GetMapping("/search")
    public ResponseEntity<?> searchJobs(
        @RequestParam(required=false) String title,
        @RequestParam(required =false)String location,
        @RequestParam(required=false)Double minSalary,
        @RequestParam(required=false)Double maxSalary,
        @RequestParam(defaultValue = "0")int page,
        @RequestParam(defaultValue="10")int size ){

            Pageable pageable=PageRequest.of(page,size);
            return ResponseEntity.ok(jobService.searchJobs(title, location, minSalary, maxSalary, pageable));
        }
    
    

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable Long id){
       
            return ResponseEntity.ok(jobService.getJobById(id));
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@PathVariable Long id,@RequestBody JobRequest request){
        
            return ResponseEntity.ok(jobService.updateJob(id,request));
       

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable Long id){
        
            jobService.deleteJob(id);
            return ResponseEntity.ok("job deleted succesfuly");
       
    }
    }
    
    

    

