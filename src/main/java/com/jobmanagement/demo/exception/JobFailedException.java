package com.jobmanagement.demo.exception;

import com.jobmanagement.demo.domain.Job;
import lombok.Getter;

@Getter
public class JobFailedException extends Exception {

    private Job job;

    public JobFailedException(Job job){
        this.job = job;
    }

}
