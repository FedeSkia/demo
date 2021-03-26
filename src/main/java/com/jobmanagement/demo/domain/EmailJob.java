package com.jobmanagement.demo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailJob extends Job {

    private String from;
    private String to;
    private String body;

    public EmailJob(Long delay, String from, String to, String body) {
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
