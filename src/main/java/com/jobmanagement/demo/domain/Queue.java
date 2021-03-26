package com.jobmanagement.demo.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

@Getter
@Setter
@Component
public class Queue {

    private BlockingQueue<Job> queue = new DelayQueue<>();

    public void addToQueue(Job job){
        queue.add(job);
    }

}
