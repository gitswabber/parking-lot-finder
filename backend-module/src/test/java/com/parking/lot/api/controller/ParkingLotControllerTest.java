package com.parking.lot.api.controller;

import com.parking.lot.api.controller.dto.ParkingLotItemResponse;
import com.parking.lot.api.controller.dto.ParkingLotRequest;
import com.parking.lot.api.controller.dto.ParkingLotResponse;
import com.parking.lot.api.service.ParkingLotService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ParkingLotController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParkingLotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingLotService parkingLotService;

    @Test
    void searchParkingLotList() throws Exception {
        ParkingLotRequest parkingLotRequest = new ParkingLotRequest();
        parkingLotRequest.setName("test name");
        parkingLotRequest.setAddress("test address");
        parkingLotRequest.setTel("123-1234");

        ParkingLotResponse parkingLotResponse = new ParkingLotResponse();
        ParkingLotItemResponse parkingLotItemResponse = new ParkingLotItemResponse();
        parkingLotItemResponse.setName(parkingLotRequest.getName());
        parkingLotItemResponse.setAddress(parkingLotRequest.getAddress());
        parkingLotItemResponse.setTel(parkingLotRequest.getTel());
        List<ParkingLotItemResponse> itemResponseList = Lists.newArrayList();
        itemResponseList.add(parkingLotItemResponse);
        parkingLotResponse.setItemResponseList(itemResponseList);
        parkingLotResponse.setTotalItemsCount(1);

        when(parkingLotService.searchParkingLotList(eq(parkingLotRequest), any(Pageable.class))).thenReturn(parkingLotResponse);

        mockMvc.perform(get("/api/v1/parking-lots?name={name}&address={address}&&tel={tel}&sort=basicParkingFee,ASC"
                , parkingLotRequest.getName(), parkingLotRequest.getAddress(), parkingLotRequest.getTel()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalItemsCount").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.itemResponseList[*].name").value(parkingLotRequest.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.itemResponseList[*].address").value(parkingLotRequest.getAddress()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.itemResponseList[*].tel").value(parkingLotRequest.getTel()));
    }
}