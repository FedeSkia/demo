package com.jobmanagement.demo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoadFromDataSourceData extends JobData {

    private String path;

    public LoadFromDataSourceData(Long delayInMilliSecond, String path){
        super(delayInMilliSecond);
        this.path = path;
    }

    @Override
    public void jobExecutionLogic() {
        System.out.println("Loading data from path " + path);
    }
}
