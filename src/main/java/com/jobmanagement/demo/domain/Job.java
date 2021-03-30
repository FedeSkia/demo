package com.jobmanagement.demo.domain;

import com.jobmanagement.demo.exception.JobFailedException;
import com.jobmanagement.demo.service.IRollback;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public abstract class Job implements Delayed, IRollback {

    protected JobState jobState = JobState.QUEUED;

    protected Long delay;

    public Job(Long delayInMilliSecond) {
        this.delay = System.currentTimeMillis() + delayInMilliSecond;;
    }

    public abstract void jobExecutionLogic() throws JobFailedException;

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = delay - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long time = delay - ((Job) o).getDelay();
        return (int) time;
    }

}
