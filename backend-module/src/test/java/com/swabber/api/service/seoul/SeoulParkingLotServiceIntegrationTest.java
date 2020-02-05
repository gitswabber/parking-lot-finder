package com.swabber.api.service.seoul;

import com.swabber.api.service.seoul.dto.SeoulParkingLot;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class SeoulParkingLotServiceIntegrationTest {

    @Autowired
    private SeoulParkingLotService seoulParkingLotService;

    @Disabled
    @Test
    void getSeoulParkingLotList() {
        final List<SeoulParkingLot> seoulParkingLotList = seoulParkingLotService.getSeoulParkingLotList(20000, 20100);
    }
}