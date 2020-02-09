package com.parking.lot.api.service.seoul.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeoulParkingLotApiResponse {

    @JsonProperty("GetParkInfo")
    private SeoulParkingLotGetParkInfo getParkInfo;
}
