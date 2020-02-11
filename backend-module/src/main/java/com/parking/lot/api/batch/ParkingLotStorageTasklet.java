package com.parking.lot.api.batch;

import com.google.common.collect.Lists;
import com.parking.lot.api.repository.ParkingLotEntity;
import com.parking.lot.api.repository.ParkingLotRepository;
import com.parking.lot.api.service.seoul.api.SeoulParkingLotService;
import com.parking.lot.api.service.seoul.api.dto.SeoulParkingLot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class ParkingLotStorageTasklet implements Tasklet, StepExecutionListener {

    private final SeoulParkingLotService seoulParkingLotService;
    private final ParkingLotRepository parkingLotRepository;

    private static final int SEARCH_SIZE = 1000;
    private int startIndex;
    private int endIndex;

    @Override
    public void beforeStep(final StepExecution stepExecution) {
        initializeIndex();
        final long count = parkingLotRepository.count();
        if (count > 0) {
            parkingLotRepository.deleteAll();
            log.info("Deleted old parking lot data. data count : {}", count);
        }
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        final List<SeoulParkingLot> seoulParkingLotList = seoulParkingLotService.getSeoulParkingLotList(startIndex, endIndex);

        if (CollectionUtils.isEmpty(seoulParkingLotList)) {
            initializeIndex();
            return RepeatStatus.FINISHED;
        }

        seoulParkingLotList.removeIf(seoulParkingLot -> parkingLotRepository.findById(seoulParkingLot.getCode()).isPresent());

        final List<ParkingLotEntity> parkingLotEntityList = mapToParkingLotEntityList(seoulParkingLotList);

        parkingLotRepository.saveAll(parkingLotEntityList);

        startIndex = endIndex + 1;
        endIndex += SEARCH_SIZE;

        return RepeatStatus.CONTINUABLE;
    }

    private void initializeIndex() {
        startIndex = 1;
        endIndex = SEARCH_SIZE;
    }

    private List<ParkingLotEntity> mapToParkingLotEntityList(List<SeoulParkingLot> seoulParkingLotList) {
        final ModelMapper modelMapper = new ModelMapper();
        List<ParkingLotEntity> parkingLotEntityList = Lists.newArrayList();
        seoulParkingLotList.forEach(seoulParkingLot -> parkingLotEntityList.add(modelMapper.map(seoulParkingLot, ParkingLotEntity.class)));
        return parkingLotEntityList;
    }

    @Override
    public ExitStatus afterStep(final StepExecution stepExecution) {
        log.info("Finished copy parking lot data into DB.");
        return stepExecution.getExitStatus();
    }
}
