package com.parking.lot.api.service;

import com.google.common.collect.Lists;
import com.parking.lot.api.controller.dto.ParkingLotItemResponse;
import com.parking.lot.api.controller.dto.ParkingLotRequest;
import com.parking.lot.api.controller.dto.ParkingLotResponse;
import com.parking.lot.api.util.DateTimeUtils;
import com.parking.lot.api.repository.ParkingLotEntity;
import com.parking.lot.api.repository.ParkingLotRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingLotService {

    private final ParkingLotMapper parkingLotMapper;
    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotResponse searchParkingLotList(ParkingLotRequest parkingLotRequest, Pageable pageable) {
        final List<ParkingLotItemResponse> itemResponseList = findParkingLotListByCriteria(parkingLotRequest, pageable);
        final int totalItemsCount = countParkingLotListByCriteria(parkingLotRequest);

        ParkingLotResponse parkingLotResponse = new ParkingLotResponse();
        parkingLotResponse.setItemResponseList(itemResponseList);
        parkingLotResponse.setTotalItemsCount(totalItemsCount);

        return parkingLotResponse;
    }

    private List<ParkingLotItemResponse> findParkingLotListByCriteria(ParkingLotRequest parkingLotRequest, Pageable pageable) {
        // todo return page
        final List<ParkingLotEntity> parkingLotEntityList = parkingLotRepository.findAll(getSpecification(parkingLotRequest), pageable).getContent();

        if (CollectionUtils.isEmpty(parkingLotEntityList)) {
            return Lists.newArrayList();
        }
        return mapToParkingLotResponseList(parkingLotEntityList);
    }

    private int countParkingLotListByCriteria(ParkingLotRequest parkingLotRequest) {
        return (int) parkingLotRepository.count(getSpecification(parkingLotRequest));
    }

    private Specification<ParkingLotEntity> getSpecification(ParkingLotRequest parkingLotRequest) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = Lists.newArrayList();

            if (StringUtils.isNotBlank(parkingLotRequest.getAddress())) {
                predicates.add(criteriaBuilder.like(root.get("address"), "%" + parkingLotRequest.getAddress() + "%"));
            }
            if (StringUtils.isNotBlank(parkingLotRequest.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + parkingLotRequest.getName() + "%"));
            }
            if (StringUtils.isNotBlank(parkingLotRequest.getTel())) {
                predicates.add(criteriaBuilder.like(root.get("tel"), "%" + parkingLotRequest.getTel() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    private List<ParkingLotItemResponse> mapToParkingLotResponseList(List<ParkingLotEntity> parkingLotEntityList) {
        List<ParkingLotItemResponse> itemResponseList = Lists.newArrayList();

        parkingLotEntityList.forEach(parkingLotEntity -> {
            final ParkingLotItemResponse parkingLotItemResponse = parkingLotMapper.mapToParkingLotItemResponse(parkingLotEntity);

            final LocalDateTime today = LocalDateTime.now();
            parkingLotItemResponse.setAvailable(isParkingAvailable(today, parkingLotEntity));
            parkingLotItemResponse.setOpeningTime(DateTimeUtils.isWeekend(today) ? parkingLotEntity.getWeekendOpeningTime() : parkingLotEntity.getWeekdayOpeningTime());
            parkingLotItemResponse.setClosingTime(DateTimeUtils.isWeekend(today) ? parkingLotEntity.getWeekendClosingTime() : parkingLotEntity.getWeekdayClosingTime());

            parkingLotItemResponse.setAvailableCount(parkingLotEntity.getParkingCapacityCount() - parkingLotEntity.getCurrentParkingCount());

            itemResponseList.add(parkingLotItemResponse);
        });

        return itemResponseList;
    }

    private boolean isParkingAvailable(LocalDateTime today, ParkingLotEntity parkingLotEntity) {
        if (parkingLotEntity.getCurrentParkingCount() < parkingLotEntity.getParkingCapacityCount()) {

            int openingTime = DateTimeUtils.isWeekend(today) ?
                    Integer.parseInt(parkingLotEntity.getWeekendOpeningTime()) : Integer.parseInt(parkingLotEntity.getWeekdayOpeningTime());
            int closingTime = DateTimeUtils.isWeekend(today) ?
                    Integer.parseInt(parkingLotEntity.getWeekendClosingTime()) : Integer.parseInt(parkingLotEntity.getWeekdayClosingTime());
            // In case business hour is 24 hours.
            if (openingTime == closingTime) {
                return true;
            }

            int currentHourMinute = Integer.parseInt(DateTimeUtils.getHourMinute(today));
            return (openingTime < currentHourMinute && currentHourMinute < closingTime);
        }
        return false;
    }
}
