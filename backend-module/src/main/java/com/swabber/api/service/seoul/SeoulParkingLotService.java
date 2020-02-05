package com.swabber.api.service.seoul;

import com.google.common.collect.Lists;
import com.swabber.api.client.RetrofitServiceGenerator;
import com.swabber.api.service.seoul.dto.SeoulParkingLot;
import com.swabber.api.service.seoul.dto.SeoulParkingLotApiClientProperties;
import com.swabber.api.service.seoul.dto.SeoulParkingLotApiResponse;
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

    // todo retryable?
    public List<SeoulParkingLot> getSeoulParkingLotList(int startIndex, int endIndex) {
        try {
            final Call<SeoulParkingLotApiResponse> seoulParkingLotApiCall = retrofitService.getSeoulParkingLotInformation(apiClientProperties.getAuthKey(), startIndex, endIndex);
            final Response<SeoulParkingLotApiResponse> apiCallResponse = seoulParkingLotApiCall.execute();
            final SeoulParkingLotApiResponse seoulParkingLotApiResponse = apiCallResponse.body();
            if (Objects.nonNull(seoulParkingLotApiResponse.getGetParkInfo())) {
                return seoulParkingLotApiResponse.getGetParkInfo().getSeoulParkingLotList();
            }
        } catch (Exception e) {
            log.error("Error has occurred while Getting parking lot information from Seoul open API. " +
                    "Auth key : {}, Search index from {} to {}.", apiClientProperties.getAuthKey(), startIndex, endIndex, e);
        }
        return Lists.newArrayList();
    }
}
