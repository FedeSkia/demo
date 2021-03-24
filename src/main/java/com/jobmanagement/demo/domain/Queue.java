package com.jobmanagement.demo.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.PriorityBlockingQueue;

@Getter
@Setter
@Component
public class Queue {

    private BlockingQueue<JobData> queue = new DelayQueue<>();

    public void addToQueue(JobData jobData){
        queue.add(jobData);
    }

}
