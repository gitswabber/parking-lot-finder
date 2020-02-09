package com.parking.lot.api.repository;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PARKING_LOT",
        indexes = {
                @Index(name = "INDEX_PARKING_NAME", columnList = "PARKING_NAME"),
                @Index(name = "INDEX_ADDR", columnList = "ADDR"),
                @Index(name = "INDEX_TEL", columnList = "TEL"),
                @Index(name = "INDEX_RATES", columnList = "RATES")
        })
public class ParkingLotEntity {
    @Id
    @Column(name = "PARKING_CODE", nullable = false)
    private Long code;

    @Column(name = "PARKING_NAME", nullable = false)
    private String name;

    @Column(name = "ADDR", nullable = false)
    private String address;

    @Column(name = "TEL", nullable = false)
    private String tel;

    @Column(name = "CAPACITY")
    private int parkingCapacityCount;

    @Column(name = "CUR_PARKING")
    private int currentParkingCount;

    @Column(name = "WEEKDAY_BEGIN_TIME")
    private String weekdayOpeningTime;

    @Column(name = "WEEKDAY_END_TIME")
    private String weekdayClosingTime;

    @Column(name = "WEEKEND_BEGIN_TIME")
    private String weekendOpeningTime;

    @Column(name = "WEEKEND_END_TIME")
    private String weekendClosingTime;

    @Column(name = "SYNC_TIME")
    private LocalDateTime updatedTime;

    @Column(name = "RATES")
    private int basicParkingFee;

    @Column(name = "TIME_RATE")
    private int basicParkingMin;
}
