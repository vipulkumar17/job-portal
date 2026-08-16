package com.jobportal.job_portal.controller;


import com.jobportal.job_portal.dto.JobRequest;
import com.jobportal.job_portal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/api/jobs")

public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping
    public ResponseEntity<?> createJob(@RequestBody JobRequest request){
        try{
            return ResponseEntity.ok(jobService.createJob(request));
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllJobs(){
        return ResponseEntity.ok(jobService.getAllJobs());
    }    

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(jobService.getJobById(id));
        }catch(RuntimeException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@PathVariable Long id,@RequestBody JobRequest request){
        try{
            return ResponseEntity.ok(jobService.updateJob(id,request));
        }
        catch(RuntimeException e){
            return ResponseEntity.status(404).body(e.getMessage());

        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable Long id){
        try{
            jobService.deleteJob(id);
            return ResponseEntity.ok("job deleted succesfuly");
        }catch(RuntimeException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    }
    
    

    

