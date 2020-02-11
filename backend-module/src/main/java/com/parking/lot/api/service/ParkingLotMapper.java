package com.parking.lot.api.service;

import com.parking.lot.api.controller.dto.ParkingLotItemResponse;
import com.parking.lot.api.repository.ParkingLotEntity;
import com.parking.lot.api.util.DateTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingLotMapper {

    private ModelMapper modelMapper;

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

    public List<ParkingLotItemResponse> mapToParkingLotItemResponseList(List<ParkingLotEntity> parkingLotEntityList) {
        return parkingLotEntityList.stream().map(this::mapToParkingLotItemResponse).collect(Collectors.toList());
    }

    public ParkingLotItemResponse mapToParkingLotItemResponse(ParkingLotEntity parkingLotEntity) {
        Assert.notNull(parkingLotEntity, "'parkingLotEntity' must not be null.");

        final ParkingLotItemResponse parkingLotItemResponse = modelMapper.map(parkingLotEntity, ParkingLotItemResponse.class);

        final LocalDateTime today = LocalDateTime.now();

        parkingLotItemResponse.setOpeningTime(DateTimeUtils.isWeekend(today) ? parkingLotEntity.getWeekendOpeningTime() : parkingLotEntity.getWeekdayOpeningTime());
        parkingLotItemResponse.setClosingTime(DateTimeUtils.isWeekend(today) ? parkingLotEntity.getWeekendClosingTime() : parkingLotEntity.getWeekdayClosingTime());

        parkingLotItemResponse.setAvailable(isParkingAvailable(today, parkingLotEntity));

        return parkingLotItemResponse;
    }

    private boolean isParkingAvailable(LocalDateTime today, ParkingLotEntity parkingLotEntity) {
        final String defaultTimeStr = "0000";

        final String openingTimeStr = StringUtils.defaultIfBlank(DateTimeUtils.isWeekend(today) ?
                parkingLotEntity.getWeekendOpeningTime() : parkingLotEntity.getWeekdayOpeningTime(), defaultTimeStr);
        final String closingTimeStr = StringUtils.defaultIfBlank(DateTimeUtils.isWeekend(today) ?
                parkingLotEntity.getWeekendClosingTime() : parkingLotEntity.getWeekdayClosingTime(), defaultTimeStr);

        // In case business hour is 24 hours.
        if (StringUtils.equals(openingTimeStr, closingTimeStr)) {
            return true;
        }

        int openingTime = Integer.parseInt(openingTimeStr);
        int closingTime = Integer.parseInt(closingTimeStr);
        int currentHourMinute = Integer.parseInt(DateTimeUtils.getHourMinute(today));
        return (openingTime < currentHourMinute && currentHourMinute < closingTime);
    }
}
