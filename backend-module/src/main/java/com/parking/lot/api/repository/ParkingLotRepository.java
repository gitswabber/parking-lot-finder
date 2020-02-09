package com.parking.lot.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ParkingLotRepository extends JpaRepository<ParkingLotEntity, Long>, JpaSpecificationExecutor<ParkingLotEntity> {
}
