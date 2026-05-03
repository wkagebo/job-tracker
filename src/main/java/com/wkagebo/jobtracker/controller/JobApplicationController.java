package com.wkagebo.jobtracker.controller;

import com.wkagebo.jobtracker.model.ApplicationStatus;
import com.wkagebo.jobtracker.model.JobApplication;
import com.wkagebo.jobtracker.service.JobApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController (JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping
    public ResponseEntity<JobApplication> createJobApplication(@RequestBody JobApplication jobApplication) {
        JobApplication savedApplication = jobApplicationService.createJobApplication(jobApplication);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedApplication);
    }

    @GetMapping
    public ResponseEntity<List<JobApplication>> getAllJobApplications() {
        return ResponseEntity.ok(jobApplicationService.getAllJobApplications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getJobApplication(@PathVariable Long id) {
        JobApplication jobApplication = jobApplicationService.getJobApplication(id);
        return ResponseEntity.ok(jobApplication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobApplication(@PathVariable Long id) {
        jobApplicationService.deleteJobApplication(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<JobApplication> updateApplicationStatus(
            @PathVariable Long id, @RequestBody ApplicationStatus status) {
        JobApplication updatedApplication = jobApplicationService.updateApplicationStatus(id, status);
        return ResponseEntity.ok(updatedApplication);
    }
}
