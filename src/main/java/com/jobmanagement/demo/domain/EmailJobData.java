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

    public EmailJobData(Long delay, String from, String to, String body) {
        super(delay);
        this.from = from;
        this.to = to;
        this.body = body;
    }

    @Override
    public void jobExecutionLogic() {
        System.out.println("Send email to " + to);
    }
}
