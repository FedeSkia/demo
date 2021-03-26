package com.jobmanagement.demo.service;

import com.jobmanagement.demo.domain.Job;
import com.jobmanagement.demo.domain.Queue;
import org.springframework.stereotype.Service;

@Service
public class JobManager {

    private final Queue queue;

    private final Thread thread;

    public JobManager(JobRunner jobRunner,
                      Queue queue) {
        this.thread = new Thread(jobRunner);
        this.queue = queue;
    }

    public void addJob(Job job) {
        queue.addToQueue(job);
        startIfNotAlready();
    }

    private void startIfNotAlready() {
        if (thread.getState().equals(Thread.State.NEW))
            thread.start();
    }

}
