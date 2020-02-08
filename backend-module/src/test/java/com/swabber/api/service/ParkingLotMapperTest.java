package com.swabber.api.service;

import com.swabber.api.controller.dto.ParkingLotItemResponse;
import com.swabber.api.repository.ParkingLotEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParkingLotMapperTest {

    private ParkingLotMapper parkingLotMapper;
    private ParkingLotEntity parkingLotEntity;

    // todo ParameterizedTest

    @BeforeEach
    void setUp() {
        parkingLotMapper = new ParkingLotMapper();
        parkingLotMapper.setUp();
        parkingLotEntity = new ParkingLotEntity();
        parkingLotEntity.setCode(1234L);
        parkingLotEntity.setName("Test name");
        parkingLotEntity.setAddress("Test address");
        parkingLotEntity.setTel("123-1234-1234");
        parkingLotEntity.setBasicParkingFee(1000);
        parkingLotEntity.setWeekdayOpeningTime("0900");
        parkingLotEntity.setWeekdayClosingTime("1700");
        parkingLotEntity.setWeekendOpeningTime("0900");
        parkingLotEntity.setWeekendClosingTime("1700");
        parkingLotEntity.setParkingCapacityCount(100);
        parkingLotEntity.setCurrentParkingCount(50);
    }

    @Test
    void mapToParkingLotItemResponse() {
        final ParkingLotItemResponse parkingLotItemResponse = parkingLotMapper.mapToParkingLotItemResponse(parkingLotEntity);
        Assertions.assertEquals(parkingLotItemResponse.getName(), parkingLotEntity.getName());
        Assertions.assertEquals(parkingLotItemResponse.getAddress(), parkingLotEntity.getAddress());
        Assertions.assertEquals(parkingLotItemResponse.getTel(), parkingLotEntity.getTel());
        Assertions.assertEquals(parkingLotItemResponse.getCode(), parkingLotEntity.getCode());
        Assertions.assertEquals(parkingLotItemResponse.getAvailableCount(), 0);
        Assertions.assertNull(parkingLotItemResponse.getClosingTime());
        Assertions.assertNull(parkingLotItemResponse.getOpeningTime());
        Assertions.assertFalse(parkingLotItemResponse.isAvailable());
    }
    // todo logbacck sjlfsjl
}