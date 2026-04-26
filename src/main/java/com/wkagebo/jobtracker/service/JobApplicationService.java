package com.wkagebo.jobtracker.service;

import com.wkagebo.jobtracker.model.ApplicationStatus;
import com.wkagebo.jobtracker.model.JobApplication;
import com.wkagebo.jobtracker.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public JobApplication createJobApplication(JobApplication jobApplication) {
        jobApplication.setDateApplied(LocalDate.now());
        return jobApplicationRepository.save(jobApplication);
    }

    public void updateApplicationStatus(Long applicationId, ApplicationStatus newStatus) {
        JobApplication application =
                jobApplicationRepository.findById(applicationId).orElseThrow(RuntimeException::new);
        application.setStatus(newStatus);
        jobApplicationRepository.save(application);
    }

    public List<JobApplication> getAllJobApplications() {
        return jobApplicationRepository.findAll();
    }

    public JobApplication getJobApplication(Long id) {
        return jobApplicationRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void deleteJobApplication(Long id) {
        jobApplicationRepository.deleteById(id);
    }
}
