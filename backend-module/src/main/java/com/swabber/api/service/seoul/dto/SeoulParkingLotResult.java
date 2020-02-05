package com.swabber.api.service.seoul.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeoulParkingLotResult {
    @JsonProperty("CODE")
    private String code;

    @JsonProperty("MESSAGE")
    private String message;
}
