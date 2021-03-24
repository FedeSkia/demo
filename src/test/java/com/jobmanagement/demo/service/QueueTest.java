package com.jobmanagement.demo.service;

import com.jobmanagement.demo.domain.EmailJobData;
import com.jobmanagement.demo.domain.JobData;
import com.jobmanagement.demo.domain.Queue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QueueTest {

    @Autowired
    Queue queue;

    @Test
    public void checkQueueOrdering() throws InterruptedException {
        JobData twoSecondsDelayJob = new EmailJobData(2000l, "", "", "");
        JobData fourSecondsDelayJob = new EmailJobData(4000l, "", "", "");
        queue.addToQueue(fourSecondsDelayJob);
        queue.addToQueue(twoSecondsDelayJob);
        JobData firstQueueElement = queue.getQueue().take();
        assertThat(firstQueueElement).isEqualTo(twoSecondsDelayJob);
    }

    @Test
    public void checkQueueOrderingInverseInsert() throws InterruptedException {
        JobData twoSecondsDelayJob = new EmailJobData(2000l, "", "", "");
        JobData fourSecondsDelayJob = new EmailJobData(4000l, "", "", "");
        queue.addToQueue(twoSecondsDelayJob);
        queue.addToQueue(fourSecondsDelayJob);
        JobData firstQueueElement = queue.getQueue().take();
        assertThat(firstQueueElement).isEqualTo(twoSecondsDelayJob);
    }

}