package com.parking.lot.api.controller;

import com.parking.lot.api.controller.dto.ErrorResponse;
import com.parking.lot.api.controller.dto.ParkingLotRequest;
import com.parking.lot.api.controller.dto.ParkingLotResponse;
import com.parking.lot.api.service.ParkingLotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "parking lot", description = "Finding parking lot API")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    @Operation(summary = "Search Seoul parking lot by query", tags = {"parking lot"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))})
    @GetMapping("parking-lots")
    public ResponseEntity<ParkingLotResponse> searchParkingLotList(
            @Parameter(schema = @Schema(implementation = ParkingLotRequest.class)) @ModelAttribute ParkingLotRequest parkingLotRequest
            , Pageable pageable) {
        final ParkingLotResponse parkingLotResponse = parkingLotService.searchParkingLotList(parkingLotRequest, pageable);
        return new ResponseEntity<>(parkingLotResponse, HttpStatus.OK);
    }
}