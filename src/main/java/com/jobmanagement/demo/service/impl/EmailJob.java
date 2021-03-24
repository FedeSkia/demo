package com.jobmanagement.demo.service.impl;

import com.jobmanagement.demo.domain.JobData;
import com.jobmanagement.demo.domain.Queue;
import org.springframework.stereotype.Service;

@Service
public class EmailJob implements Runnable {

    private final Queue queue;

    public EmailJob(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("Polling...");
        while (true) {
            try {
                JobData jobData = queue.getQueue().take();
                jobData.execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
