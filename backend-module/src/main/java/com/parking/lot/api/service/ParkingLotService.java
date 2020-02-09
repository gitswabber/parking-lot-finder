package com.parking.lot.api.service;

import com.parking.lot.api.controller.dto.ParkingLotItemResponse;
import com.parking.lot.api.controller.dto.ParkingLotRequest;
import com.parking.lot.api.controller.dto.ParkingLotResponse;
import com.parking.lot.api.repository.ParkingLotEntity;
import com.parking.lot.api.repository.ParkingLotRepository;
import com.parking.lot.api.repository.ParkingLotSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingLotService {

    private final ParkingLotMapper parkingLotMapper;
    private final ParkingLotSpecification parkingLotSpecification;
    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotResponse searchParkingLotList(ParkingLotRequest parkingLotRequest, Pageable pageable) {
        final Specification<ParkingLotEntity> specification = parkingLotSpecification.getSpecification(parkingLotRequest.getAddress(), parkingLotRequest.getName(), parkingLotRequest.getTel());
        final Page<ParkingLotEntity> parkingLotEntityPage = parkingLotRepository.findAll(specification, pageable);

        final List<ParkingLotItemResponse> itemResponseList = parkingLotMapper.mapToParkingLotItemResponseList(parkingLotEntityPage.getContent());

        ParkingLotResponse parkingLotResponse = new ParkingLotResponse();
        parkingLotResponse.setItemResponseList(itemResponseList);
        parkingLotResponse.setTotalItemsCount(parkingLotEntityPage.getTotalElements());

        return parkingLotResponse;
    }

}
