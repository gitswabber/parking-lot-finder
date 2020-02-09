package com.parking.lot.api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLotResponse {
    private List<ParkingLotItemResponse> itemResponseList;
    private long totalItemsCount;
}
