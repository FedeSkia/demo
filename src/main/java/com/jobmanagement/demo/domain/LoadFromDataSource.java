package com.jobmanagement.demo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoadFromDataSource extends Job {

    private String pathToFile;

    public LoadFromDataSource(Long delay, String pathToFile){
        super(delay);
        this.pathToFile = pathToFile;
    }

    @Override
    public void jobExecutionLogic() {
        System.out.println("Loading data from path " + pathToFile);
    }
}
