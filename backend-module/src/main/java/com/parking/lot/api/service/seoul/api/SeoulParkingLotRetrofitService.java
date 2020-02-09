package com.parking.lot.api.service.seoul.api;

import com.parking.lot.api.service.seoul.api.dto.SeoulParkingLotApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SeoulParkingLotRetrofitService {
    @GET("{authKey}/json/GetParkInfo/{startIndex}/{endIndex}")
    Call<SeoulParkingLotApiResponse> getSeoulParkingLotInformation(
            @Path("authKey") String authKey, @Path("startIndex") int startIndex, @Path("endIndex") int endIndex);
}
