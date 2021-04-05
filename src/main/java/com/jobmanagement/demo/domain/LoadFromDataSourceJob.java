package com.jobmanagement.demo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoadFromDataSourceJob extends Job {

    private String pathToFile;

    public LoadFromDataSourceJob(Long delay, String pathToFile){
        super(delay);
        this.pathToFile = pathToFile;
    }

    @Override
    public String getJobType() {
        return "loadFromData";
    }

    @Override
    public void jobExecutionLogic() {
        System.out.println("Loading data from path " + pathToFile);
    }

    @Override
    public void rollback() {
        System.out.println("Rollback SQL");
    }
}
