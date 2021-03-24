package com.jobmanagement.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.concurrent.Delayed;

@Getter
@Setter
public abstract class JobData implements Delayed {

    protected JobState jobState = JobState.QUEUED;

    protected Long delay;

    public JobData(Long delayInMilliSecond){
        this.delay = System.currentTimeMillis() + delayInMilliSecond;;
    }

    public abstract void execute();

}
