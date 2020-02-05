package com.swabber.api.repository;

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

    @Column(name = "PARKING_TYPE_NM")
    private String type;

    @Column(name = "OPERATION_RULE_NM")
    private String operationRule;

    @Column(name = "TEL", nullable = false)
    private String tel;

    @Column(name = "CAPACITY")
    private String parkingCapacityCount;

    @Column(name = "PAY_NM")
    private String payType;

    @Column(name = "NIGHT_FREE_OPEN_NM")
    private String nightFreeOpen;

    @Column(name = "WEEKDAY_BEGIN_TIME")
    private String weekdayOpeningTime;

    @Column(name = "WEEKDAY_END_TIME")
    private String weekdayClosingTime;

    @Column(name = "WEEKEND_BEGIN_TIME")
    private String weekendOpeningTime;

    @Column(name = "WEEKEND_END_TIME")
    private String weekendClosingTime;

    @Column(name = "HOLIDAY_BEGIN_TIME")
    private String holidayOpeningTime;

    @Column(name = "HOLIDAY_END_TIME")
    private String holidayClosingTime;

    @Column(name = "SYNC_TIME")
    private LocalDateTime updatedTime;

    @Column(name = "SATURDAY_PAY_NM")
    private String saturdayPayType;

    @Column(name = "HOLIDAY_PAY_YN")
    private String holidayPayType;

    @Column(name = "RATES")
    private Double basicParkingFee;

    @Column(name = "TIME_RATE")
    private Double basicParkingMin;

    @Column(name = "ADD_RATES")
    private Double additionalParkingFee;

    @Column(name = "ADD_TIME_RATE")
    private Double additionalParkingMin;
}
