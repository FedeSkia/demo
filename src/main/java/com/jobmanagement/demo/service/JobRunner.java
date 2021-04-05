package com.jobmanagement.demo.service;

import com.jobmanagement.demo.domain.Job;
import com.jobmanagement.demo.domain.JobState;
import com.jobmanagement.demo.exception.JobFailedException;
import com.jobmanagement.demo.repository.entities.JobEntity;
import com.jobmanagement.demo.repository.entities.JobRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

@Slf4j
@Component
public class JobRunner implements Runnable {

    @Getter
    private final BlockingQueue<Job> queue;

    private final JobRepository jobRepository;

    public JobRunner(JobRepository jobRepository) {
        this.queue = new DelayQueue<Job>();
        this.jobRepository = jobRepository;
    }

    @Override
    public void run() {
        System.out.println("Polling...");
        while (true) {
            try {
                Job job = queue.take();
                job.jobExecutionLogic();
                saveToJobTable(JobState.SUCCESS.name(), job.getJobType());
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            } catch (JobFailedException e) {
                e.getJob().rollback();
                log.error(e.getMessage());
                saveToJobTable(JobState.FAILED.name(), e.getJob().getJobType());
            }
        }
    }

    private void saveToJobTable(String state, String jobType) {
        JobEntity jobEntity = new JobEntity();
        jobEntity.setStatus(state);
        jobEntity.setType(jobType);
        jobRepository.save(jobEntity);
    }

}
