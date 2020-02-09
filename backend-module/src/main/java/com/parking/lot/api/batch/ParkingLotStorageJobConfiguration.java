package com.parking.lot.api.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ParkingLotStorageJobConfiguration {

    private final JobBuilderFactory jobBuilder;
    private final StepBuilderFactory stepBuilder;

    @Bean
    public TaskletStep parkingLotStorageStep(ParkingLotStorageTasklet tasklet) {
        return stepBuilder.get("parkingLotStorageTasklet").tasklet(tasklet).build();
    }

    @Bean
    public Job parkingLotStorageJob(Step parkingLotStorageStep) {
        return jobBuilder.get("parkingLotStorageJob")
                .incrementer(new RunIdIncrementer())
                .start(parkingLotStorageStep)
                .build();
    }
}