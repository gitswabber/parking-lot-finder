package com.swabber.api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLotItemResponse {
    private Long code;
    private String name;
    private String address;
    private String tel;
    private String openingTime;
    private String closingTime;
    private boolean available;
}
