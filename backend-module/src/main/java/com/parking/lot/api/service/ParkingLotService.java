package com.parking.lot.api.service;

import com.google.common.collect.Lists;
import com.parking.lot.api.controller.dto.ParkingLotItemResponse;
import com.parking.lot.api.controller.dto.ParkingLotRequest;
import com.parking.lot.api.controller.dto.ParkingLotResponse;
import com.parking.lot.api.repository.ParkingLotEntity;
import com.parking.lot.api.repository.ParkingLotRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.Predicate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingLotService {

    private final ParkingLotMapper parkingLotMapper;
    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotResponse searchParkingLotList(ParkingLotRequest parkingLotRequest, Pageable pageable) {
        final Page<ParkingLotEntity> parkingLotEntityPage = parkingLotRepository.findAll(getSpecification(parkingLotRequest), pageable);
        final List<ParkingLotItemResponse> itemResponseList = mapToParkingLotResponseList(parkingLotEntityPage.getContent());

        ParkingLotResponse parkingLotResponse = new ParkingLotResponse();
        parkingLotResponse.setItemResponseList(itemResponseList);
        parkingLotResponse.setTotalItemsCount(parkingLotEntityPage.getTotalElements());

        return parkingLotResponse;
    }

    private Specification<ParkingLotEntity> getSpecification(ParkingLotRequest parkingLotRequest) {
        Assert.notNull(parkingLotRequest, "'parkingLotRequest' must not be null.");

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            final String address = parkingLotRequest.getAddress();
            final String name = parkingLotRequest.getName();
            final String tel = parkingLotRequest.getTel();

            if (StringUtils.isNotBlank(address)) {
                predicates.add(criteriaBuilder.like(root.get("address"), "%" + address + "%"));
            }
            if (StringUtils.isNotBlank(name)) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            if (StringUtils.isNotBlank(tel)) {
                predicates.add(criteriaBuilder.like(root.get("tel"), "%" + tel + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    private List<ParkingLotItemResponse> mapToParkingLotResponseList(List<ParkingLotEntity> parkingLotEntityList) {
        List<ParkingLotItemResponse> itemResponseList = Lists.newArrayList();

        parkingLotEntityList.forEach(parkingLotEntity -> {
            itemResponseList.add(parkingLotMapper.mapToParkingLotItemResponse(parkingLotEntity));
        });

        return itemResponseList;
    }
}
