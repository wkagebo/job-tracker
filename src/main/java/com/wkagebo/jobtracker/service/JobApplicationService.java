package com.wkagebo.jobtracker.service;

import com.wkagebo.jobtracker.messaging.StatusUpdateProducer;
import com.wkagebo.jobtracker.model.ApplicationStatus;
import com.wkagebo.jobtracker.model.JobApplication;
import com.wkagebo.jobtracker.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final StatusUpdateProducer statusUpdateProducer;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository,
                                 StatusUpdateProducer statusUpdateProducer) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.statusUpdateProducer = statusUpdateProducer;
    }

    public JobApplication createJobApplication(JobApplication jobApplication) {
        jobApplication.setDateApplied(LocalDate.now());
        return jobApplicationRepository.save(jobApplication);
    }

    public JobApplication updateApplicationStatus(Long applicationId, ApplicationStatus newStatus) {
        JobApplication application =
                jobApplicationRepository.findById(applicationId).orElseThrow(RuntimeException::new);
        application.setStatus(newStatus);
        JobApplication saved = jobApplicationRepository.save(application);
        statusUpdateProducer.sendStatusUpdate(applicationId, newStatus);
        return saved;
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
