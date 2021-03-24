package com.jobmanagement.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class EmailJobData extends JobData {

    private String from;
    private String to;
    private String body;

    public EmailJobData(Long delay, String from, String to, String body, Long delayInMilliseconds) {
        super(delay);
        this.from = from;
        this.to = to;
        this.body = body;
    }

    @Override
    public void execute() {
        System.out.println("Send email");
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = delay - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long time = delay - ((EmailJobData) o).getDelay();
        return (int) time;
    }
}
