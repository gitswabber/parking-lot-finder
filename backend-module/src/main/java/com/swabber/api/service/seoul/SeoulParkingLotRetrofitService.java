package com.swabber.api.service.seoul;

import com.swabber.api.service.seoul.dto.SeoulParkingLotApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SeoulParkingLotRetrofitService {
    @GET("/{authKey}/json/GetParkInfo/{startIndex}/{endIndex}")
    Call<SeoulParkingLotApiResponse> getSeoulParkingLotInformation(String authKey, int startIndex, int endIndex);
}
