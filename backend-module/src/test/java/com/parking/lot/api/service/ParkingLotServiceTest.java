package com.parking.lot.api.service;

import com.parking.lot.api.controller.dto.ParkingLotItemResponse;
import com.parking.lot.api.controller.dto.ParkingLotRequest;
import com.parking.lot.api.controller.dto.ParkingLotResponse;
import com.parking.lot.api.repository.ParkingLotEntity;
import com.parking.lot.api.repository.ParkingLotRepository;
import com.parking.lot.api.repository.ParkingLotSpecification;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class ParkingLotServiceTest {

    @Mock
    private ParkingLotMapper parkingLotMapper;

    @Mock
    private ParkingLotSpecification parkingLotSpecification;

    @Mock
    private ParkingLotRepository parkingLotRepository;

    @InjectMocks
    private ParkingLotService parkingLotService;

    private static Stream<Arguments> provideForSearchParkingLotList() {
        ParkingLotRequest parkingLotRequest = new ParkingLotRequest();
        parkingLotRequest.setName("test1");
        Pageable pageable = PageRequest.of(0, 3);
        List<ParkingLotEntity> parkingLotEntityList = Lists.newArrayList();
        parkingLotEntityList.add(new ParkingLotEntity());
        parkingLotEntityList.add(new ParkingLotEntity());
        parkingLotEntityList.add(new ParkingLotEntity());
        Page<ParkingLotEntity> parkingLotEntityPage = new PageImpl<>(parkingLotEntityList);
        List<ParkingLotItemResponse> itemResponseList = Lists.newArrayList();
        itemResponseList.add(new ParkingLotItemResponse());
        itemResponseList.add(new ParkingLotItemResponse());
        itemResponseList.add(new ParkingLotItemResponse());

        ParkingLotRequest parkingLotRequest2 = new ParkingLotRequest();
        parkingLotRequest2.setName("test2");
        Pageable pageable2 = PageRequest.of(0, 2);
        List<ParkingLotEntity> parkingLotEntityList2 = Lists.newArrayList();
        parkingLotEntityList2.add(new ParkingLotEntity());
        parkingLotEntityList2.add(new ParkingLotEntity());
        Page<ParkingLotEntity> parkingLotEntityPage2 = new PageImpl<>(parkingLotEntityList2);
        List<ParkingLotItemResponse> itemResponseList2 = Lists.newArrayList();
        itemResponseList2.add(new ParkingLotItemResponse());
        itemResponseList2.add(new ParkingLotItemResponse());

        return Stream.of(
                Arguments.of(parkingLotRequest, pageable, parkingLotEntityPage, itemResponseList, 3),
                Arguments.of(parkingLotRequest2, pageable2, parkingLotEntityPage2, itemResponseList2, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("provideForSearchParkingLotList")
    void searchParkingLotList(ParkingLotRequest parkingLotRequest, Pageable pageable, Page<ParkingLotEntity> parkingLotEntityPage, List<ParkingLotItemResponse> itemResponseList, int expected) {
        Specification specification = mock(Specification.class);

        when(parkingLotSpecification.getSpecification(parkingLotRequest.getAddress(), parkingLotRequest.getName(), parkingLotRequest.getTel())).thenReturn(specification);
        when(parkingLotRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(parkingLotEntityPage);

        when(parkingLotMapper.mapToParkingLotItemResponseList(parkingLotEntityPage.getContent())).thenReturn(itemResponseList);

        final ParkingLotResponse parkingLotResponse = parkingLotService.searchParkingLotList(parkingLotRequest, pageable);

        verify(parkingLotRepository, times(1)).findAll(any(Specification.class), eq(pageable));
        verify(parkingLotMapper, times(1)).mapToParkingLotItemResponseList(any(List.class));
        Assertions.assertEquals(expected, parkingLotResponse.getTotalItemsCount());
        Assertions.assertEquals(expected, parkingLotResponse.getItemResponseList().size());
    }
}