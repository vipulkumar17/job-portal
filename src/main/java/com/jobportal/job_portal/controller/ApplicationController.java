package com.jobportal.job_portal.controller;

import com.jobportal.job_portal.dto.ApplicationRequest;
import com.jobportal.job_portal.dto.StatusUpdateRequest;
import com.jobportal.job_portal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<?> apply(@RequestBody ApplicationRequest request) {
        try {
            return ResponseEntity.ok(applicationService.apply(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getApplicationsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(applicationService.getApplicationByUserId(userId));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<?> getApplicationsByJob(@PathVariable Long jobId) {
        return ResponseEntity.ok(applicationService.getApplicationByJobId(jobId));
    }

    @PutMapping("/{id}/status")  // note the extra path segment /status — distinguishes this from a full resource update
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody StatusUpdateRequest request) {
        try {
            return ResponseEntity.ok(applicationService.updateStatus(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}