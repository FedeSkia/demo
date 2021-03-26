package com.jobmanagement.demo.converter;

import com.jobmanagement.demo.domain.Job;
import com.jobmanagement.demo.repository.entities.JobEntity;
import org.springframework.stereotype.Component;

@Component
public class JobConverter {

    public JobEntity toEntity(Job job){
        JobEntity entity = new JobEntity();
        entity.setStatus(job.getJobState().name());
        entity.setType("email");
        return entity;
    }

}
