package com.jobmanagement.demo.service;

import com.jobmanagement.demo.domain.EmailJob;
import com.jobmanagement.demo.domain.Job;
import com.jobmanagement.demo.repository.entities.JobRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.BlockingQueue;

import static org.assertj.core.api.Assertions.assertThat;

class QueueTest {

    @Autowired
    JobRepository jobRepository;

    JobRunner jobRunner = new JobRunner(jobRepository);

    @Test
    public void checkQueueOrdering() throws InterruptedException {
        Job twoSecondsDelayJob = new EmailJob(2000l, "", "", "");
        Job fourSecondsDelayJob = new EmailJob(4000l, "", "", "");
        BlockingQueue<Job> queue = jobRunner.getQueue();
        queue.add(fourSecondsDelayJob);
        queue.add(twoSecondsDelayJob);

        Job firstQueueElement = queue.take();
        assertThat(firstQueueElement).isEqualTo(twoSecondsDelayJob);
    }

    @Test
    public void checkQueueOrderingInverseInsert() throws InterruptedException {
        Job twoSecondsDelayJob = new EmailJob(2000l, "", "", "");
        Job fourSecondsDelayJob = new EmailJob(4000l, "", "", "");
        BlockingQueue<Job> queue = jobRunner.getQueue();
        queue.add(twoSecondsDelayJob);
        queue.add(fourSecondsDelayJob);
        Job firstQueueElement = queue.take();
        assertThat(firstQueueElement).isEqualTo(twoSecondsDelayJob);
    }

}