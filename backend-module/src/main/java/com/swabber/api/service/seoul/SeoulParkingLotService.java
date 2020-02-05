package com.swabber.api.service.seoul;

import com.swabber.api.client.RetrofitApiClient;
import com.swabber.api.client.RetrofitServiceGenerator;
import com.swabber.api.service.seoul.dto.SeoulParkingLot;
import com.swabber.api.service.seoul.dto.SeoulParkingLotApiClientProperties;
import com.swabber.api.service.seoul.dto.SeoulParkingLotApiResponse;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.util.List;

@Service
public class SeoulParkingLotService {

    private final SeoulParkingLotApiClientProperties apiClientProperties;

    private final SeoulParkingLotRetrofitService retrofitService;

    //    @Autowired
    public SeoulParkingLotService(SeoulParkingLotApiClientProperties apiClientProperties) {
        this.apiClientProperties = apiClientProperties;
        this.retrofitService = RetrofitServiceGenerator.createService(apiClientProperties, SeoulParkingLotRetrofitService.class);
    }

    public List<SeoulParkingLot> getSeoulParkingLotList(int startIndex, int endIndex) {
        final Call<SeoulParkingLotApiResponse> seoulParkingLotInformation = retrofitService.getSeoulParkingLotInformation(apiClientProperties.getAuthKey(), startIndex, endIndex);
        RetrofitApiClient.executeAndGetBody(seoulParkingLotInformation);
    }
}
