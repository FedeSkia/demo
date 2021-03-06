package com.jobmanagement.demo.service;

import com.jobmanagement.demo.TestMysqlDatabase;
import com.jobmanagement.demo.domain.EmailJob;
import com.jobmanagement.demo.domain.Job;
import com.jobmanagement.demo.domain.JobState;
import com.jobmanagement.demo.exception.JobFailedException;
import com.jobmanagement.demo.repository.entities.JobEntity;
import com.jobmanagement.demo.repository.entities.JobRepository;
import lombok.extern.slf4j.Slf4j;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class JobManagerTestFail {

    @ClassRule
    public static MySQLContainer mySQLContainer = TestMysqlDatabase.getInstance();

    JobManager jobManager;

    JobRepository jobRepository = Mockito.mock(JobRepository.class);

    JobRunner jobRunner;

    @BeforeEach
    public void prepareTests() {
        jobRunner = new JobRunner(jobRepository);
        jobManager = new JobManager(jobRunner);
    }

    @Test
    public void whenJobFailsPersistsWithFAILEDState() throws JobFailedException {
        Job emailJobData = Mockito.mock(EmailJob.class);
        when(emailJobData.getDelay()).thenReturn(0L);
        when(emailJobData.getDelay(any())).thenReturn(0L);
        doCallRealMethod().when(emailJobData).rollback();
        when(emailJobData.getJobState()).thenReturn(JobState.FAILED);
        doThrow(new JobFailedException(emailJobData)).when(emailJobData).jobExecutionLogic();
        jobManager.addJob(emailJobData);
        verify(jobRepository, after(1000)).save(any());
    }

}