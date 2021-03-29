package com.jobmanagement.demo.service;

import com.jobmanagement.demo.converter.JobConverter;
import com.jobmanagement.demo.domain.EmailJob;
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
            Job job = null;
            try {
                job = queue.getQueue().take();
                job.jobExecutionLogic();
                saveToJobTable(job, JobState.SUCCESS.name());
            } catch (Exception e) {
                saveToJobTable(job, JobState.FAILED.name());
            }
        }
    }

    private void saveToJobTable(Job job, String state) {
        if( job == null ) {
            job = new EmailJob(0L,"", "", "");
        }
        JobEntity jobEntity = jobConverter.toEntity(job);
        jobEntity.setStatus(state);
        jobRepository.save(jobEntity);
    }

}
