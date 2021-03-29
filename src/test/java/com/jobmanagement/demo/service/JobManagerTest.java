package com.jobmanagement.demo.service;

import com.jobmanagement.demo.TestMysqlDatabase;
import com.jobmanagement.demo.converter.JobConverter;
import com.jobmanagement.demo.domain.EmailJob;
import com.jobmanagement.demo.domain.Job;
import com.jobmanagement.demo.domain.Queue;
import com.jobmanagement.demo.repository.entities.JobEntity;
import com.jobmanagement.demo.repository.entities.JobRepository;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.MySQLContainer;

import java.util.List;
import java.util.concurrent.DelayQueue;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JobManagerTest {

    @ClassRule
    public static MySQLContainer mySQLContainer = TestMysqlDatabase.getInstance();

    Queue queue;

    JobManager jobManager;

    @Autowired
    JobRepository jobRepository;

    JobConverter jobConverter;

    JobRunner jobRunner;

    @BeforeEach
    public void prepareTests() {
        queue = new Queue(new DelayQueue());
        jobConverter = new JobConverter();
        jobRunner = new JobRunner(queue, jobRepository, jobConverter);
        jobManager = new JobManager(jobRunner, queue);
    }

    @Test
    public void addAJobWithZeroDelayWillBeImmediatelyExecuted() throws InterruptedException {
        Job emailJobData = new EmailJob(0L, "federico", "payoneer", "");
        jobManager.addJob(emailJobData);
        Long countJobs = jobRepository.count();
        assertEquals(1, countJobs);
    }

}