package com.parking.lot.api.repository;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.batch.JpaBatchConfigurer;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// todo
//@ExtendWith(SpringR.class)
@DataJpaTest
class ParkingLotRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ParkingLotRepository parkingLotRepository;
//
//    @Autowired
//    private ParkingLotSpecification parkingLotSpecification;

//    @BeforeEach
//    void setUp() {
//    }

//    @Disabled
    @Test
    void findAll() {

//        List<ParkingLotEntity> parkingLotEntityList = Lists.newArrayList();
//        parkingLotEntityList.add(createParkingLotEntity(111L));
//        parkingLotEntityList.add(createParkingLotEntity(222L));
//        parkingLotEntityList.add(createParkingLotEntity(333L));
//        parkingLotEntityList.add(createParkingLotEntity(444L));

        final ParkingLotEntity entity = new ParkingLotEntity();
        entity.setCode(111L);
        entity.setName("test name");
        entity.setAddress("test address");
        entity.setTel("111-1111");
        entity.setBasicParkingFee(100);
        entityManager.persist(entity);
        entityManager.flush();


        final List<ParkingLotEntity> all = parkingLotRepository.findAll();

        System.out.println(all.size());
    }

    private ParkingLotEntity createParkingLotEntity(long code) {
        final ParkingLotEntity parkingLotEntity = new ParkingLotEntity();
        parkingLotEntity.setCode(code);
        return parkingLotEntity;
    }
}