package com.swabber.api.service;

import com.google.common.collect.Lists;
import com.swabber.api.controller.dto.ParkingLotRequest;
import com.swabber.api.controller.dto.ParkingLotResponse;
import com.swabber.api.repository.ParkingLotEntity;
import com.swabber.api.repository.ParkingLotRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Predicate;
import java.util.List;

@Service
public class ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public List<ParkingLotResponse> searchParkingLotList(ParkingLotRequest parkingLotRequest, Pageable pageable) {
        return findParkingLotListByCriteria(parkingLotRequest, pageable);
    }

    private List<ParkingLotResponse> findParkingLotListByCriteria(ParkingLotRequest parkingLotRequest, Pageable pageable) {

        final List<ParkingLotEntity> parkingLotEntityList = parkingLotRepository.findAll((Specification<ParkingLotEntity>) (root, query, criteriaBuilder) -> {
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
                }
                , pageable).getContent();

        if (CollectionUtils.isEmpty(parkingLotEntityList)) {
            return Lists.newArrayList();
        }

        return mapToParkingLotResponse(parkingLotEntityList);
    }

    private List<ParkingLotResponse> mapToParkingLotResponse(List<ParkingLotEntity> parkingLotEntityList) {
        final ModelMapper modelMapper = new ModelMapper();
        List<ParkingLotResponse> parkingLotResponseList = Lists.newArrayList();
        parkingLotEntityList.forEach(parkingLotEntity -> parkingLotResponseList.add(modelMapper.map(parkingLotEntity, ParkingLotResponse.class)));
        return parkingLotResponseList;
    }
}
