package com.jobmanagement.demo.controller;

import com.jobmanagement.demo.domain.EmailJob;
import com.jobmanagement.demo.domain.LoadFromDataSourceJob;
import com.jobmanagement.demo.service.JobManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    private final JobManager jobManager;

    public JobController(JobManager jobManager) {
        this.jobManager = jobManager;
    }

    @PostMapping("/add-email-job")
    public void addEmailJob(@RequestBody EmailJob addJobDTO) {
        jobManager.addJob(addJobDTO);
    }

    @PostMapping("/add-load-job")
    public void addDataSourceJob(@RequestBody LoadFromDataSourceJob dataSource) {
        jobManager.addJob(dataSource);
    }

}
