package com.swabber.api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLotResponse {
    private List<ParkingLotItemResponse> itemResponseList;
    private int totalItemsCount;
    private boolean available;
}
