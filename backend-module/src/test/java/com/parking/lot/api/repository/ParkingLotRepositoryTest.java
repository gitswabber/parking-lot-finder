package com.parking.lot.api.repository;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;


@DataJpaTest
class ParkingLotRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    private ParkingLotSpecification parkingLotSpecification;
    private List<ParkingLotEntity> parkingLotEntityList;

    @BeforeEach
    void setUp() {
        parkingLotSpecification = new ParkingLotSpecification();
        parkingLotEntityList = Lists.newArrayList();
        parkingLotEntityList.add(createParkingLotEntity(111L));
        parkingLotEntityList.add(createParkingLotEntity(222L));
        parkingLotEntityList.add(createParkingLotEntity(333L));
        parkingLotEntityList.forEach(parkingLotEntity -> {
            entityManager.persist(parkingLotEntity);
            entityManager.flush();
        });
    }

    @Disabled
    @Test
    void findAll() {
        final List<ParkingLotEntity> all = parkingLotRepository.findAll();
        Assertions.assertEquals(all.size(), 3);
    }

    @Disabled
    @Test
    void findById() {
        final PageRequest pageRequest = PageRequest.of(0, 1);
        final Page<ParkingLotEntity> parkingLotEntityPage = parkingLotRepository.findAll(parkingLotSpecification.getSpecification("", "111", ""), pageRequest);
        Assertions.assertNotNull(parkingLotEntityPage.getContent());
    }

    private ParkingLotEntity createParkingLotEntity(long code) {
        final ParkingLotEntity parkingLotEntity = new ParkingLotEntity();
        parkingLotEntity.setCode(code);
        parkingLotEntity.setName("test" + code);
        parkingLotEntity.setAddress("test address");
        parkingLotEntity.setTel("111-1111");
        parkingLotEntity.setBasicParkingFee(100);
        return parkingLotEntity;
    }
}