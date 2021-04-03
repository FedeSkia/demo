package com.jobmanagement.demo.service;

import com.jobmanagement.demo.domain.Job;
import org.springframework.stereotype.Service;

@Service
public class JobManager {

    private final Thread thread;
    private final JobRunner jobRunner;

    public JobManager(JobRunner jobRunner) {
        this.jobRunner = jobRunner;
        this.thread = new Thread(jobRunner);
    }

    public void addJob(Job job) {
        jobRunner.getQueue().add(job);
        startIfNotAlready();
    }

    private void startIfNotAlready() {
        if (thread.getState().equals(Thread.State.NEW))
            thread.start();
    }

}
