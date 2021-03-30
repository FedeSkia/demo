package com.jobmanagement.demo.service;

import com.jobmanagement.demo.converter.JobConverter;
import com.jobmanagement.demo.domain.EmailJob;
import com.jobmanagement.demo.domain.Job;
import com.jobmanagement.demo.domain.JobState;
import com.jobmanagement.demo.domain.Queue;
import com.jobmanagement.demo.exception.JobFailedException;
import com.jobmanagement.demo.repository.entities.JobEntity;
import com.jobmanagement.demo.repository.entities.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

@Slf4j
@Component
public class JobRunner implements Runnable, IRollback {

    private final BlockingQueue<Job> queue;

    private final JobRepository jobRepository;

    public JobRunner(BlockingQueue<Job> queue, JobRepository jobRepository) {
        this.queue = queue;
        this.jobRepository = jobRepository;
    }

    @Override
    public void run() {
        System.out.println("Polling...");
        while (true) {
            try {
                Job job = queue.getQueue().take();
                job.jobExecutionLogic();
                saveToJobTable(JobState.SUCCESS.name());
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            } catch (JobFailedException e) {
                e.getJob().rollback();
                saveToJobTable(JobState.FAILED.name());
            }
        }
    }

    private void saveToJobTable(String state) {
        JobEntity jobEntity = new JobEntity();
        jobEntity.setStatus(state);
        jobRepository.save(jobEntity);
    }

    @Override
    public void rollback() {
        System.out.println("Rollback");
    }
}
