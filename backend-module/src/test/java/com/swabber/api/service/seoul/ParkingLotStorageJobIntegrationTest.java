package com.swabber.api.service.seoul;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

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
        final JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date()).toJobParameters();

        jobLauncher.run(parkingLotStorageJob, jobParameters);
    }
}
