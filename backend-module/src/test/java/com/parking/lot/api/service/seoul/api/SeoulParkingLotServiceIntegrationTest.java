package com.parking.lot.api.service.seoul.api;

import com.parking.lot.api.service.seoul.api.dto.SeoulParkingLot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class SeoulParkingLotServiceIntegrationTest {

    @Autowired
    private SeoulParkingLotService seoulParkingLotService;

    @Test
    void getSeoulParkingLotList() {
        final List<SeoulParkingLot> seoulParkingLotList = seoulParkingLotService.getSeoulParkingLotList(1, 1000);
        Assertions.assertNotNull(seoulParkingLotList);
    }
}