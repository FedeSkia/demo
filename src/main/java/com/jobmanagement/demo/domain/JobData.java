package com.jobmanagement.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class JobData implements Comparable<JobData> {

    protected JobState jobState = JobState.QUEUED;

    protected LocalDateTime scheduled;

    public JobData(LocalDateTime scheduled){
        this.scheduled = scheduled;
    }

    public abstract void execute();

}
