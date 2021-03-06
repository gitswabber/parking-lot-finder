package com.parking.lot.api.service.seoul.api;

import com.google.common.collect.Lists;
import com.parking.lot.api.client.RetrofitServiceGenerator;
import com.parking.lot.api.exception.ParkingLotException;
import com.parking.lot.api.service.seoul.api.dto.SeoulParkingLot;
import com.parking.lot.api.service.seoul.api.dto.SeoulParkingLotApiClientProperties;
import com.parking.lot.api.service.seoul.api.dto.SeoulParkingLotApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class SeoulParkingLotService {

    private final SeoulParkingLotApiClientProperties apiClientProperties;

    private SeoulParkingLotRetrofitService retrofitService;

    public SeoulParkingLotService(final @Qualifier("seoulParkingLotApiClientProperties") SeoulParkingLotApiClientProperties apiClientProperties) {
        this.apiClientProperties = apiClientProperties;
        this.retrofitService = RetrofitServiceGenerator.createService(apiClientProperties, SeoulParkingLotRetrofitService.class);
    }

    public List<SeoulParkingLot> getSeoulParkingLotList(int startIndex, int endIndex) {
        try {
            final Call<SeoulParkingLotApiResponse> seoulParkingLotApiCall = retrofitService.getSeoulParkingLotInformation(apiClientProperties.getAuthKey(), startIndex, endIndex);
            final Response<SeoulParkingLotApiResponse> apiCallResponse = seoulParkingLotApiCall.execute();
            final SeoulParkingLotApiResponse seoulParkingLotApiResponse = apiCallResponse.body();

            if (Objects.nonNull(seoulParkingLotApiResponse.getGetParkInfo())) {
                return seoulParkingLotApiResponse.getGetParkInfo().getSeoulParkingLotList();
            }
            return Lists.newArrayList();
        } catch (Exception e) {
            throw new ParkingLotException(String.format("Error has occurred while getting parking lot information from Seoul open API. " +
                    "Auth key : %s, Search index from %s to %s.", apiClientProperties.getAuthKey(), startIndex, endIndex), e);
        }
    }
}
