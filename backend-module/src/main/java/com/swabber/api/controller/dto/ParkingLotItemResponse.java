package com.swabber.api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLotItemResponse {
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
    private String updatedTime;
    private String saturdayPayType;
    private String holidayPayType;
    private Double basicParkingFee;
    private Double basicParkingMin;
    private Double additionalParkingFee;
    private Double additionalParkingMin;
}
