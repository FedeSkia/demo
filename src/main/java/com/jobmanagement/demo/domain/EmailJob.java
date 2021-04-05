package com.jobmanagement.demo.domain;

import com.jobmanagement.demo.exception.JobFailedException;
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
    public String getJobType() {
        return "EmailJob";
    }

    @Override
    public void jobExecutionLogic() throws JobFailedException {
        System.out.println("Send email to " + to);
    }

    @Override
    public void rollback() {
        System.out.println("Rollback email sent");
    }
}
