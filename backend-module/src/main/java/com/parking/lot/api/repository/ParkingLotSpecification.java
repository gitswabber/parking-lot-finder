package com.parking.lot.api.repository;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.List;

@Component
public class ParkingLotSpecification {

    public Specification<ParkingLotEntity> getSpecification(String address, String name, String tel) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = Lists.newArrayList();

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
}
