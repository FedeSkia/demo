package com.jobmanagement.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmailJobData extends JobData {

    private String from;

    private String to;

    private String body;

    public EmailJobData(LocalDateTime scheduled, String from, String to, String body) {
        super(scheduled);
        this.from = from;
        this.to = to;
        this.body = body;
    }

    @Override
    public int compareTo(JobData o) {
        if(o.getScheduled().isBefore(getScheduled())){
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public void execute() {
        System.out.println("Send email");
    }
}
