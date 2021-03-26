package com.jobmanagement.demo.service;

import com.jobmanagement.demo.domain.EmailJob;
import com.jobmanagement.demo.domain.Job;
import com.jobmanagement.demo.domain.Queue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QueueTest {

    @Autowired
    Queue queue;

    @Test
    public void checkQueueOrdering() throws InterruptedException {
        Job twoSecondsDelayJob = new EmailJob(2000l, "", "", "");
        Job fourSecondsDelayJob = new EmailJob(4000l, "", "", "");
        queue.addToQueue(fourSecondsDelayJob);
        queue.addToQueue(twoSecondsDelayJob);
        Job firstQueueElement = queue.getQueue().take();
        assertThat(firstQueueElement).isEqualTo(twoSecondsDelayJob);
    }

    @Test
    public void checkQueueOrderingInverseInsert() throws InterruptedException {
        Job twoSecondsDelayJob = new EmailJob(2000l, "", "", "");
        Job fourSecondsDelayJob = new EmailJob(4000l, "", "", "");
        queue.addToQueue(twoSecondsDelayJob);
        queue.addToQueue(fourSecondsDelayJob);
        Job firstQueueElement = queue.getQueue().take();
        assertThat(firstQueueElement).isEqualTo(twoSecondsDelayJob);
    }

}