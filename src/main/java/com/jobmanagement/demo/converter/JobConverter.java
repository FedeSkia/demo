package com.jobmanagement.demo.converter;

import com.jobmanagement.demo.domain.EmailJobData;
import com.jobmanagement.demo.domain.JobData;
import com.jobmanagement.demo.repository.entities.JobEntity;
import org.springframework.stereotype.Component;

@Component
public class JobConverter {

    public JobEntity toEntity(JobData job){
        JobEntity entity = new JobEntity();
        entity.setStatus(job.getJobState().name());
        entity.setType("email");
        return entity;
    }

}
