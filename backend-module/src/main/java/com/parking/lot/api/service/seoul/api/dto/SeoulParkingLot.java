package com.parking.lot.api.service.seoul.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeoulParkingLot {
    @JsonProperty("PARKING_CODE")
    private Long code;

    @JsonProperty("PARKING_NAME")
    private String name;

    @JsonProperty("ADDR")
    private String address;

    @JsonProperty("TEL")
    private String tel;

    @JsonProperty("CAPACITY")
    private int parkingCapacityCount;

    @JsonProperty("CUR_PARKING")
    private int currentParkingCount;

    @JsonProperty("WEEKDAY_BEGIN_TIME")
    private String weekdayOpeningTime;

    @JsonProperty("WEEKDAY_END_TIME")
    private String weekdayClosingTime;

    @JsonProperty("WEEKEND_BEGIN_TIME")
    private String weekendOpeningTime;

    @JsonProperty("WEEKEND_END_TIME")
    private String weekendClosingTime;

    @JsonProperty("SYNC_TIME")
    private String updatedTime;

    @JsonProperty("RATES")
    private int basicParkingFee;

    @JsonProperty("TIME_RATE")
    private int basicParkingMin;
}
