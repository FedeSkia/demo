package com.jobmanagement.demo.service;

import com.jobmanagement.demo.TestMysqlDatabase;
import com.jobmanagement.demo.domain.EmailJob;
import com.jobmanagement.demo.domain.Job;
import com.jobmanagement.demo.repository.entities.JobRepository;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.MySQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JobManagerTest {

    @ClassRule
    public static MySQLContainer mySQLContainer = TestMysqlDatabase.getInstance();

    JobManager jobManager;

    @Autowired
    JobRepository jobRepository;

    JobRunner jobRunner;

    @BeforeEach
    public void prepareTests() {
        jobRunner = new JobRunner(jobRepository);
        jobManager = new JobManager(jobRunner);
    }

    @Test
    public void addAJobWithZeroDelayWillBeImmediatelyExecuted() {
        Job emailJobData = new EmailJob(0L, "federico", "payoneer", "");
        jobManager.addJob(emailJobData);
        Long countJobs = jobRepository.count();
        assertEquals(1, countJobs);
    }

}