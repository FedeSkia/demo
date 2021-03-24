package com.jobmanagement.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Demo {

    public static void main(String args[]) throws InterruptedException {

        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();


        Thread thread = new Thread(() -> {
            System.out.println("Polling...");
            while (true) {
                try {
                    Integer poll = queue.take();
                    System.out.println("Polled: " + poll);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        System.out.println("Adding to queue");

        queue.addAll(Arrays.asList(1, 5, 6, 1, 2, 6, 7));
        Thread.sleep(TimeUnit.SECONDS.toMillis(1));

    }

}
