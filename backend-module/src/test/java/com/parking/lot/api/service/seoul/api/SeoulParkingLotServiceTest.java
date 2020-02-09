package com.parking.lot.api.service.seoul.api;

import com.parking.lot.api.service.seoul.api.dto.SeoulParkingLotApiClientProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class SeoulParkingLotServiceTest {

    @Mock
    private SeoulParkingLotApiClientProperties apiClientProperties;

    @Mock
    private SeoulParkingLotRetrofitService retrofitService;

    @InjectMocks
    private SeoulParkingLotService seoulParkingLotService;

    @Test
    void getSeoulParkingLotList() throws IOException {
        Call call = mock(Call.class);
        Response response = mock(Response.class);

        when(retrofitService.getSeoulParkingLotInformation(anyString(), anyInt(), anyInt())).thenReturn(call);
        when(call.execute()).thenReturn(response);
//        when(response.body())
    }
}