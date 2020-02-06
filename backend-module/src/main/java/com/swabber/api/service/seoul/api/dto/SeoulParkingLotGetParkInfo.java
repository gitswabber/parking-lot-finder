package com.swabber.api.service.seoul.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeoulParkingLotGetParkInfo {
    @JsonProperty("list_total_count")
    private long totalCount;

    @JsonProperty("RESULT")
    private SeoulParkingLotResult result;

    @JsonProperty("row")
    private List<SeoulParkingLot> seoulParkingLotList;
}
