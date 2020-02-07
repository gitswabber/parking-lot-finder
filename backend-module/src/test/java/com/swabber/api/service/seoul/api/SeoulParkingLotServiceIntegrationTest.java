package com.swabber.api.service.seoul.api;

import com.swabber.api.service.seoul.api.dto.SeoulParkingLot;
import org.junit.jupiter.api.Disabled;
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

    @Disabled
    @Test
    void getSeoulParkingLotList() {
        final List<SeoulParkingLot> seoulParkingLotList = seoulParkingLotService.getSeoulParkingLotList(1, 1000);
    }
}