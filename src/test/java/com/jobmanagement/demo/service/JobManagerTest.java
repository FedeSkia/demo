package com.jobmanagement.demo.service;

import com.jobmanagement.demo.converter.JobConverter;
import com.jobmanagement.demo.domain.EmailJobData;
import com.jobmanagement.demo.domain.JobData;
import com.jobmanagement.demo.domain.Queue;
import com.jobmanagement.demo.repository.entities.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JobManagerTest {

    Queue queue;

    JobManager jobManager;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    JobConverter jobConverter;

    @BeforeEach
    public void prepareTests(){
        queue = new Queue();
        EmailJob emailJob = new EmailJob(queue, jobRepository, jobConverter);
        jobManager = new JobManager(emailJob, queue);
    }

    @Test
    public void addAJobWithZeroDelayWillBeImmediatelyExecuted() throws InterruptedException {
        JobData emailJobData = new EmailJobData(0L, "", "", "");
        jobManager.addJob(emailJobData);
        assertNull(queue.getQueue().take());
    }

}