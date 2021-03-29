package com.jobmanagement.demo.service;

import com.jobmanagement.demo.domain.EmailJob;
import com.jobmanagement.demo.domain.Job;
import com.jobmanagement.demo.domain.Queue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.DelayQueue;

import static org.assertj.core.api.Assertions.assertThat;

class QueueTest {

    Queue queue = new Queue(new DelayQueue());

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