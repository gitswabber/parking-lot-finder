package com.parking.lot.api.service;

import com.parking.lot.api.controller.dto.ParkingLotItemResponse;
import com.parking.lot.api.repository.ParkingLotEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

@Component
public final class ParkingLotMapper {

    private static ModelMapper modelMapper;

    @PostConstruct
    public void setUp() {
        modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<ParkingLotEntity, ParkingLotItemResponse>() {
            @Override
            protected void configure() {
                skip().setAvailable(false);
                skip().setOpeningTime(null);
                skip().setClosingTime(null);
            }
        });
    }

    public ParkingLotItemResponse mapToParkingLotItemResponse(ParkingLotEntity parkingLotEntity){
        Assert.notNull(parkingLotEntity, "'parkingLotEntity' must not be null.");
        return modelMapper.map(parkingLotEntity, ParkingLotItemResponse.class);
    }
}
