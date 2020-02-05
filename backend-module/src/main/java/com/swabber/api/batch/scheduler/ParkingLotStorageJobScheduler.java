package com.swabber.api.batch.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class ParkingLotStorageJobScheduler {
    private final JobLauncher jobLauncher;
    private final Job parkingLotStorageJob;

    private static final int FIVE_MINUTES = 1000 * 60 * 5;

    @Scheduled(fixedRate = FIVE_MINUTES)
    public void storeSeoulParkingLotIntoDB() throws Exception {
        final JobParameters jobParameters = new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
        jobLauncher.run(parkingLotStorageJob, jobParameters);
    }
}
