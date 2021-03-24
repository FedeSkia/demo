package com.jobmanagement.demo.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.concurrent.PriorityBlockingQueue;

@Getter
@Setter
@Component
public class Queue {

    private PriorityBlockingQueue<JobData> queue = new PriorityBlockingQueue<>();

    public void addToQueue(JobData jobData){
        queue.add(jobData);
    }

}
