package com.jobmanagement.demo.service;

import com.jobmanagement.demo.converter.JobConverter;
import com.jobmanagement.demo.domain.Job;
import com.jobmanagement.demo.domain.JobState;
import com.jobmanagement.demo.domain.Queue;
import com.jobmanagement.demo.repository.entities.JobEntity;
import com.jobmanagement.demo.repository.entities.JobRepository;
import org.springframework.stereotype.Component;

@Component
public class JobRunner implements Runnable {

    private final Queue queue;

    private final JobRepository jobRepository;

    private final JobConverter jobConverter;

    public JobRunner(Queue queue, JobRepository jobRepository, JobConverter jobConverter) {
        this.queue = queue;
        this.jobRepository = jobRepository;
        this.jobConverter = jobConverter;
    }

    @Override
    public void run() {
        System.out.println("Polling...");
        while (true) {
            try {
                Job job = queue.getQueue().take();
                job.jobExecutionLogic();
                JobEntity jobEntity = jobConverter.toEntity(job);
                jobEntity.setStatus(JobState.SUCCESS.name());
                jobRepository.save(jobEntity);
            } catch (Exception e) {
                JobEntity jobEntity = new JobEntity();
                jobEntity.setStatus(JobState.FAILED.name());
                jobEntity.setType("email");
                jobRepository.save(jobEntity);
            }
        }
    }

}
