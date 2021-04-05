# demo

Implemented 2 type of Job: Email and LoadFromDataSource.
Obviously there's no business logic (they will print out to console that job is done) but you can verify
the job status opening a database connection (localhost:3306/jobmanager)

Check classes schema in root folder (JobExecutor.drawio)

Launch tests using: gradlew test

Usage:
a) create database using docker-compose located in /database
b) launch app using gradlew bootrun
c) invoke endpoints to trigger new job with delay (there's a postman collection located in /postman folder or use http://localhost:8080/swagger-ui/ )

