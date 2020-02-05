package com.swabber.api.service.seoul;

import com.swabber.api.service.seoul.dto.SeoulParkingLotApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SeoulParkingLotRetrofitService {
    @GET("{authKey}/json/GetParkInfo/{startIndex}/{endIndex}")
    Call<SeoulParkingLotApiResponse> getSeoulParkingLotInformation(
            @Path("authKey") String authKey, @Path("startIndex") int startIndex, @Path("endIndex") int endIndex);
}
