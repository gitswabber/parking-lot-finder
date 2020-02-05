package com.swabber.api.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ParkingLotStorageJobConfiguration {

    private final JobBuilderFactory jobBuilder;
    private final StepBuilderFactory stepBuilder;

}