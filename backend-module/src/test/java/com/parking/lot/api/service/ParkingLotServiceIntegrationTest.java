package com.parking.lot.api.service;

import com.parking.lot.api.controller.dto.ParkingLotRequest;
import com.parking.lot.api.repository.ParkingLotEntity;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class ParkingLotServiceIntegrationTest {

    @Autowired
    private ParkingLotService parkingLotService;

    List<ParkingLotEntity> parkingLotEntityList = Lists.newArrayList();

    @BeforeEach
    public void setUp() {
        ParkingLotEntity parkingLotEntity = new ParkingLotEntity();
        parkingLotEntity.setName("탄천주차장(구)");
        parkingLotEntity.setAddress("강남구 삼성동 171-0");
        parkingLotEntity.setTel("02-2176-0901");
        parkingLotEntity.setCode(173437L);

        ParkingLotEntity parkingLotEntity2 = new ParkingLotEntity();
        parkingLotEntity2.setName("대청역(구)");
        parkingLotEntity2.setAddress("강남구 개포동 13-2");
        parkingLotEntity2.setTel("02-2176-0940");
        parkingLotEntity2.setCode(173472L);

        parkingLotEntityList.add(parkingLotEntity);
        parkingLotEntityList.add(parkingLotEntity2);
    }

    @Test
    void searchParkingLotList() {
        final Pageable pageable = PageRequest.of(0, 10, Sort.by("basicParkingFee")).toOptional().get();
        final Pageable pageable2 = PageRequest.of(0, 20, Sort.by("basicParkingFee")).toOptional().get();

        ParkingLotRequest parkingLotRequest = new ParkingLotRequest("탄천", "", "");
        parkingLotService.searchParkingLotList(parkingLotRequest, pageable);
    }
}