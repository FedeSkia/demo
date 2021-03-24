package com.jobmanagement.demo.service;

import com.jobmanagement.demo.domain.JobData;
import com.jobmanagement.demo.domain.Queue;
import com.jobmanagement.demo.service.impl.EmailJob;
import org.springframework.stereotype.Service;

@Service
public class JobManager {

    private final Queue queue;

    private final Thread thread;

    public JobManager(EmailJob emailJob,
                      Queue queue) {
        this.thread = new Thread(emailJob);
        this.queue = queue;
    }

    public void addJob(JobData jobData) {
        queue.addToQueue(jobData);
        startIfNotAlready();
    }

    private void startIfNotAlready() {
        if (thread.getState().equals(Thread.State.NEW))
            thread.start();
    }

}
