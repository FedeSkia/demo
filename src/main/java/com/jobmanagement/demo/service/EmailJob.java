package com.jobmanagement.demo.service;

import com.jobmanagement.demo.domain.JobData;
import com.jobmanagement.demo.domain.Queue;
import org.springframework.stereotype.Component;

@Component
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
