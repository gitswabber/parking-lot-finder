package com.swabber.api.service.seoul;

import com.swabber.api.repository.ParkingLotEntity;
import com.swabber.api.service.seoul.dto.SeoulParkingLot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

public class ModelMapperTest {

    private ModelMapper modelMapper = new ModelMapper();

    private SeoulParkingLot seoulParkingLot;

    @BeforeEach
    public void setUp() {
        seoulParkingLot = new SeoulParkingLot();
        seoulParkingLot.setCode(1234L);
        seoulParkingLot.setName("Test name");
        seoulParkingLot.setAddress("Test address");
        seoulParkingLot.setBasicParkingFee(1000D);
    }

    @Disabled
    @Test
    public void mapToParkingLotEntityList() {
        ParkingLotEntity parkingLotEntity = modelMapper.map(seoulParkingLot, ParkingLotEntity.class);
        Assertions.assertEquals(seoulParkingLot.getCode(), parkingLotEntity.getCode());
        Assertions.assertEquals(seoulParkingLot.getName(), parkingLotEntity.getName());
        Assertions.assertEquals(seoulParkingLot.getAddress(), parkingLotEntity.getAddress());
        Assertions.assertEquals(seoulParkingLot.getBasicParkingFee(), parkingLotEntity.getBasicParkingFee());
    }
}
