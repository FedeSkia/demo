package com.jobmanagement.demo.service;

import com.jobmanagement.demo.domain.Job;
import com.jobmanagement.demo.domain.Queue;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

@Service
public class JobManager {

    private final BlockingQueue<Job> queue;

    private final Thread thread;

    public JobManager(JobRunner jobRunner) {
        this.thread = new Thread(jobRunner);
        this.queue = new DelayQueue<>();
    }

    public void addJob(Job job) {
        queue.add(job);
        startIfNotAlready();
    }

    private void startIfNotAlready() {
        if (thread.getState().equals(Thread.State.NEW))
            thread.start();
    }

}
