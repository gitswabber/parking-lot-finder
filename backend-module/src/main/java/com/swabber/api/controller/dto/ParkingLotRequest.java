package com.swabber.api.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingLotRequest {
    private String name;
    private String address;
    private String tel;
}
