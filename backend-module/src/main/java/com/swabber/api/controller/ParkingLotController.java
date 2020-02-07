package com.swabber.api.controller;

import com.swabber.api.controller.dto.ParkingLotRequest;
import com.swabber.api.controller.dto.ParkingLotResponse;
import com.swabber.api.service.ParkingLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    @GetMapping("parking-lots")
    public ResponseEntity<ParkingLotResponse> searchParkingLotList(@ModelAttribute ParkingLotRequest parkingLotRequest, Pageable pageable) {
        final ParkingLotResponse parkingLotResponse = parkingLotService.searchParkingLotList(parkingLotRequest, pageable);
        return new ResponseEntity<>(parkingLotResponse, HttpStatus.OK);
    }
}