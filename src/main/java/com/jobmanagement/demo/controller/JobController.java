package com.jobmanagement.demo.controller;

import com.jobmanagement.demo.domain.EmailJobData;
import com.jobmanagement.demo.domain.LoadFromDataSourceData;
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

    @PostMapping("/addJob")
    public void addJob(@RequestBody EmailJobData addJobDTO) {
        jobManager.addJob(addJobDTO);
    }

    @PostMapping("/addDataSourceJob")
    public void addJob(@RequestBody LoadFromDataSourceData addJobDTO) {
        jobManager.addJob(addJobDTO);
    }

}
