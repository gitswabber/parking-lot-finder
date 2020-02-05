package com.swabber.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParkingLot {
    private Long code;
    private String name;
    private String address;
    private String type;
    private String operationRule;
    private String tel;
    private String parkingCapacityCount;
    private String payType;
    private String nightFreeOpen;
    private String weekdayOpeningTime;
    private String weekdayClosingTime;
    private String weekendOpeningTime;
    private String weekendClosingTime;
    private String holidayOpeningTime;
    private String holidayClosingTime;
    private LocalDateTime updatedTime;
    private String saturdayPayType;
    private String holidayPayType;
    private Double basicParkingFee;
    private Double basicParkingMin;
    private Double additionalParkingFee;
    private Double additionalParkingMin;
}
