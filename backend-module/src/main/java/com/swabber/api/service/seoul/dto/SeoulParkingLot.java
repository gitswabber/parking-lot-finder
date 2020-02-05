package com.swabber.api.service.seoul.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeoulParkingLot {
    @JsonProperty("PARKING_CODE")
    private Long code;

    @JsonProperty("PARKING_NAME")
    private String name;

    @JsonProperty("ADDR")
    private String address;

    @JsonProperty("PARKING_TYPE")
    private String type;

    @JsonProperty("OPERATION_RULE")
    private String operationRule;

    @JsonProperty("TEL")
    private String tel;

    @JsonProperty("CAPACITY")
    private String parkingCapacityCount;

    @JsonProperty("PAY_NM")
    private String payType;

    @JsonProperty("NIGHT_FREE_OPEN")
    private String nightFreeOpen;

    @JsonProperty("WEEKDAY_BEGIN_TIME")
    private String weekdayOpeningTime;

    @JsonProperty("WEEKDAY_END_TIME")
    private String weekdayClosingTime;

    @JsonProperty("WEEKEND_BEGIN_TIME")
    private String weekendOpeningTime;

    @JsonProperty("WEEKEND_END_TIME")
    private String weekendClosingTime;

    @JsonProperty("HOLIDAY_BEGIN_TIME")
    private String holidayOpeningTime;

    @JsonProperty("HOLIDAY_END_TIME")
    private String holidayClosingTime;

    @JsonProperty("SYNC_TIME")
    private String updatedTime;

    @JsonProperty("SATURDAY_PAY_NM")
    private String saturdayPayType;

    @JsonProperty("HOLIDAY_PAY_NM")
    private String holidayPayType;

    @JsonProperty("RATES")
    private Double basicParkingFee;

    @JsonProperty("TIME_RATE")
    private Double basicParkingMin;

    @JsonProperty("ADD_RATES")
    private Double additionalParkingFee;

    @JsonProperty("ADD_TIME_RATE")
    private Double additionalParkingMin;
}
