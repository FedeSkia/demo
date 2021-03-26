package com.jobmanagement.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public abstract class JobData implements Delayed {

    protected JobState jobState = JobState.QUEUED;

    protected Long delay;

    public JobData(Long delayInMilliSecond){
        this.delay = System.currentTimeMillis() + delayInMilliSecond;;
    }

    public abstract void jobExecutionLogic();

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = delay - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long time = delay - ((JobData) o).getDelay();
        return (int) time;
    }

}
