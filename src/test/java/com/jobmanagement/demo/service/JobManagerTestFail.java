package com.jobmanagement.demo.service;

import com.jobmanagement.demo.TestMysqlDatabase;
import com.jobmanagement.demo.converter.JobConverter;
import com.jobmanagement.demo.domain.EmailJob;
import com.jobmanagement.demo.domain.Job;
import com.jobmanagement.demo.domain.JobState;
import com.jobmanagement.demo.domain.Queue;
import com.jobmanagement.demo.repository.entities.JobEntity;
import com.jobmanagement.demo.repository.entities.JobRepository;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.MySQLContainer;

import java.util.List;
import java.util.concurrent.DelayQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JobManagerTestFail {

    @ClassRule
    public static MySQLContainer mySQLContainer = TestMysqlDatabase.getInstance();

    DelayQueue delayQueue = new DelayQueue();

    Queue queue = new Queue(delayQueue);

    JobManager jobManager;

    @Autowired
    JobRepository jobRepository;

    JobConverter jobConverter;

    JobRunner jobRunner;

    @BeforeEach
    public void prepareTests() {
        jobConverter = new JobConverter();
        jobRunner = new JobRunner(queue, jobRepository, jobConverter);
        jobManager = new JobManager(jobRunner, queue);
    }

    @Test
    public void whenJobFailsPersistsWithFAILEDState() throws InterruptedException {
        when(delayQueue.take()).thenThrow(new Exception());
        Job emailJobData = new EmailJob(0L, "federico", "", "");
        jobManager.addJob(emailJobData);
        List<JobEntity> jobList = jobRepository.findAll();
        assertEquals(jobList.stream().findFirst().get().getStatus(), JobState.FAILED.name());
    }

}