package com.jobmanagement.demo;

import org.testcontainers.containers.MySQLContainer;

public class TestMysqlDatabase extends MySQLContainer<TestMysqlDatabase> {

    private static final String IMAGE_VERSION = "mysql:latest";
    private static TestMysqlDatabase container;

    private TestMysqlDatabase() {
        super(IMAGE_VERSION);
    }

    public static TestMysqlDatabase getInstance() {
        if (container == null) {
            container = new TestMysqlDatabase()
                    .withDatabaseName("reuseme");
            container.start();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }


}
