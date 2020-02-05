package com.swabber.api.batch;

import com.google.common.collect.Lists;
import com.swabber.api.repository.ParkingLotEntity;
import com.swabber.api.repository.ParkingLotRepository;
import com.swabber.api.service.seoul.SeoulParkingLotService;
import com.swabber.api.service.seoul.dto.SeoulParkingLot;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@RequiredArgsConstructor
@StepScope
@Component
public class ParkingLotStorageTasklet implements Tasklet {

    private final SeoulParkingLotService seoulParkingLotService;
    private final ParkingLotRepository parkingLotRepository;

    private static final int SEARCH_ITEM_COUNT = 1000;
    private int startIndex;
    private int endIndex;

    @PostConstruct
    public void setUp() {
        startIndex = 1;
        endIndex = SEARCH_ITEM_COUNT;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        final List<SeoulParkingLot> seoulParkingLotList = seoulParkingLotService.getSeoulParkingLotList(startIndex, endIndex);

        if (CollectionUtils.isEmpty(seoulParkingLotList)) {
            return RepeatStatus.FINISHED;
        }

        final List<ParkingLotEntity> parkingLotEntityList = mapToParkingLotEntityList(seoulParkingLotList);

        parkingLotRepository.saveAll(parkingLotEntityList);

        // todo
//        final ParkingLotEntity one = parkingLotRepository.findById(1010089L).get();

        startIndex = endIndex + 1;
        endIndex += SEARCH_ITEM_COUNT;

        return RepeatStatus.CONTINUABLE;
    }

    private List<ParkingLotEntity> mapToParkingLotEntityList(List<SeoulParkingLot> seoulParkingLotList) {
        final ModelMapper modelMapper = new ModelMapper();
        List<ParkingLotEntity> parkingLotEntityList = Lists.newArrayList();
        seoulParkingLotList.forEach(seoulParkingLot -> parkingLotEntityList.add(modelMapper.map(seoulParkingLot, ParkingLotEntity.class)));
        return parkingLotEntityList;
    }
}
