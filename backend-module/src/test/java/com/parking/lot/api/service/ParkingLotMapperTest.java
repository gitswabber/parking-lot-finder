package com.parking.lot.api.service;

import com.parking.lot.api.controller.dto.ParkingLotItemResponse;
import com.parking.lot.api.repository.ParkingLotEntity;
import com.parking.lot.api.util.DateTimeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

class ParkingLotMapperTest {

    private static ParkingLotMapper parkingLotMapper;

    @BeforeAll
    static void setUp() {
        parkingLotMapper = new ParkingLotMapper();
        parkingLotMapper.setUp();
    }

    private static Stream<Arguments> provideForMapToParkingLotItemResponse() {
        ParkingLotEntity parkingLotEntity;
        parkingLotEntity = new ParkingLotEntity();
        parkingLotEntity.setCode(1234L);
        parkingLotEntity.setName("Test name");
        parkingLotEntity.setAddress("Test address");
        parkingLotEntity.setTel("123-1234-1234");
        parkingLotEntity.setBasicParkingFee(1000);
        parkingLotEntity.setWeekdayOpeningTime("0000");
        parkingLotEntity.setWeekdayClosingTime("0000");
        parkingLotEntity.setWeekendOpeningTime("0000");
        parkingLotEntity.setWeekendClosingTime("0000");
        parkingLotEntity.setParkingCapacityCount(100);
        parkingLotEntity.setCurrentParkingCount(50);

        ParkingLotEntity parkingLotEntity2;
        parkingLotEntity2 = new ParkingLotEntity();
        parkingLotEntity2.setCode(1234323L);
        parkingLotEntity2.setName("Test name2");
        parkingLotEntity2.setAddress("Test address2");
        parkingLotEntity2.setTel("123-1234-1234");
        parkingLotEntity2.setBasicParkingFee(1000);
        parkingLotEntity2.setWeekdayOpeningTime("0000");
        parkingLotEntity2.setWeekdayClosingTime("0000");
        parkingLotEntity2.setWeekendOpeningTime("0000");
        parkingLotEntity2.setWeekendClosingTime("0000");
        parkingLotEntity2.setParkingCapacityCount(50);
        parkingLotEntity2.setCurrentParkingCount(50);

        return Stream.of(
                Arguments.of(parkingLotEntity, 50, true),
                Arguments.of(parkingLotEntity2, 0, false)
        );
    }

    @MethodSource("provideForMapToParkingLotItemResponse")
    @ParameterizedTest
    void mapToParkingLotItemResponse(ParkingLotEntity parkingLotEntity, int expectedCount, boolean expected) {
        final ParkingLotItemResponse parkingLotItemResponse = parkingLotMapper.mapToParkingLotItemResponse(parkingLotEntity);
        Assertions.assertEquals(parkingLotItemResponse.getName(), parkingLotEntity.getName());
        Assertions.assertEquals(parkingLotItemResponse.getAddress(), parkingLotEntity.getAddress());
        Assertions.assertEquals(parkingLotItemResponse.getTel(), parkingLotEntity.getTel());
        Assertions.assertEquals(parkingLotItemResponse.getCode(), parkingLotEntity.getCode());
        Assertions.assertEquals(parkingLotItemResponse.getAvailableCount(), expectedCount);

        final LocalDateTime today = LocalDateTime.now();

        if (DateTimeUtils.isWeekend(today)) {
            Assertions.assertEquals(parkingLotEntity.getWeekendOpeningTime(), parkingLotItemResponse.getOpeningTime());
            Assertions.assertEquals(parkingLotEntity.getWeekendClosingTime(), parkingLotItemResponse.getClosingTime());
        } else {
            Assertions.assertEquals(parkingLotEntity.getWeekdayOpeningTime(), parkingLotItemResponse.getOpeningTime());
            Assertions.assertEquals(parkingLotEntity.getWeekdayClosingTime(), parkingLotItemResponse.getClosingTime());
        }

        Assertions.assertEquals(expected, parkingLotItemResponse.isAvailable());
    }
}