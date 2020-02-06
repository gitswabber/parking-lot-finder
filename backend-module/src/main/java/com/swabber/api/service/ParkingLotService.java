package com.swabber.api.service;

import com.swabber.api.controller.dto.ParkingLotRequest;
import com.swabber.api.controller.dto.ParkingLotResponse;
import com.swabber.api.repository.ParkingLotRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }


    public List<ParkingLotResponse> searchParkingLotList(ParkingLotRequest parkingLotRequest, Pageable pageable) {
        
    }
}
