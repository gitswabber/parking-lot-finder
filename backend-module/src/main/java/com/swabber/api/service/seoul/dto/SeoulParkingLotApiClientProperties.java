package com.swabber.api.service.seoul.dto;

import com.swabber.api.client.ApiClientProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Getter
@Setter
@PropertySource("classpath:properties/client/seoul-parking-lot-api-${spring.profiles.active}.properties")
@EnableConfigurationProperties(SeoulParkingLotApiClientProperties.class)
@ConfigurationProperties(prefix = "seoul.parking.lot.api")
public class SeoulParkingLotApiClientProperties extends ApiClientProperties {
    private String authKey;
}
