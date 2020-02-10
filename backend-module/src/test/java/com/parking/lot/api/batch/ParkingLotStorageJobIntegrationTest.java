package com.parking.lot.api.batch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ParkingLotStorageJobIntegrationTest {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private Job parkingLotStorageJob;

    @Test
    void parkingLotStorageJob() throws Exception {
        final SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepository);
        simpleJobLauncher.afterPropertiesSet();
        final JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
        jobLauncherTestUtils.setJobLauncher(simpleJobLauncher);
        jobLauncherTestUtils.setJobRepository(jobRepository);
        jobLauncherTestUtils.setJob(parkingLotStorageJob);

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(new JobParametersBuilder().addDate("date", new Date()).toJobParameters());
        JobInstance actualJobInstance = jobExecution.getJobInstance();

        Assertions.assertEquals(actualJobInstance.getJobName(), "parkingLotStorageJob");
    }
}
