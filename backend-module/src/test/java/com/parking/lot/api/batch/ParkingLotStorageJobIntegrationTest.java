package com.parking.lot.api.batch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

@SpringBootTest
@ActiveProfiles("test")
public class ParkingLotStorageJobIntegrationTest {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job parkingLotStorageJob;

    @Test
    public void parkingLotStorageJob() throws Exception {
        final JobParameters jobParameters = new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
        final JobExecution jobExecution = jobLauncher.run(parkingLotStorageJob, jobParameters);
        Assertions.assertEquals(jobExecution.getJobInstance().getJobName(), "parkingLotStorageJob");
    }
}