package com.swabber.api.service;

import com.google.common.collect.Lists;
import com.swabber.api.controller.dto.ParkingLotItemResponse;
import com.swabber.api.controller.dto.ParkingLotRequest;
import com.swabber.api.controller.dto.ParkingLotResponse;
import com.swabber.api.repository.ParkingLotEntity;
import com.swabber.api.repository.ParkingLotRepository;
import com.swabber.api.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;

    private static ModelMapper modelMapper;

    @PostConstruct
    public void setUp() {
        modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<ParkingLotEntity, ParkingLotItemResponse>() {
            @Override
            protected void configure() {
                skip().setAvailable(false);
                skip().setOpeningTime(null);
                skip().setClosingTime(null);
            }
        });
    }

    public ParkingLotResponse searchParkingLotList(ParkingLotRequest parkingLotRequest, Pageable pageable) {
        final List<ParkingLotItemResponse> itemResponseList = findParkingLotListByCriteria(parkingLotRequest, pageable);
        final int totalItemsCount = countParkingLotListByCriteria(parkingLotRequest);

        ParkingLotResponse parkingLotResponse = new ParkingLotResponse();
        parkingLotResponse.setItemResponseList(itemResponseList);
        parkingLotResponse.setTotalItemsCount(totalItemsCount);

        return parkingLotResponse;
    }

    private List<ParkingLotItemResponse> findParkingLotListByCriteria(ParkingLotRequest parkingLotRequest, Pageable pageable) {
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
        return (Specification<ParkingLotEntity>) (root, query, criteriaBuilder) -> {
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
            final ParkingLotItemResponse parkingLotItemResponse = modelMapper.map(parkingLotEntity, ParkingLotItemResponse.class);

            final LocalDateTime today = LocalDateTime.now();
            parkingLotItemResponse.setAvailable(isParkingAvailable(today, parkingLotEntity));
            parkingLotItemResponse.setOpeningTime(DateTimeUtils.isWeekend(today) ? parkingLotEntity.getWeekendOpeningTime() : parkingLotEntity.getWeekdayOpeningTime());
            parkingLotItemResponse.setClosingTime(DateTimeUtils.isWeekend(today) ? parkingLotEntity.getWeekendClosingTime() : parkingLotEntity.getWeekdayClosingTime());

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
            if (openingTime == closingTime) {
                return true;
            }

            int currentHourMinute = Integer.parseInt(DateTimeUtils.getHourMinute(today));
            return (openingTime < currentHourMinute && currentHourMinute < closingTime);
        }
        return false;
    }
}
