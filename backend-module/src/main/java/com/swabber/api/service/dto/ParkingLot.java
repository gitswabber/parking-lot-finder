package com.swabber.api.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLot {
    private Long code;
    private String name;
    private String address;
    private String tel;
    private int parkingCapacityCount;
    private int currentParkingCount;
    private String weekdayOpeningTime;
    private String weekdayClosingTime;
    private String weekendOpeningTime;
    private String weekendClosingTime;
    private LocalDateTime updatedTime;
    private int basicParkingFee;
    private int basicParkingMin;
}
