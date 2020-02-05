package com.swabber.api.service.seoul.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeoulParkingLotApiResponse {

    @JsonProperty("GetParkInfo")
    private SeoulParkingLotGetParkInfo getParkInfo;
}
