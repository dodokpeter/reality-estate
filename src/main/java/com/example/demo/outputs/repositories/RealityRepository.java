package com.example.demo.outputs.repositories;

import com.example.demo.outputs.entities.RealityEntity;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.List;
import java.util.stream.Stream;

// todo 5: diff between jpa and crud ?
@Repository
public interface RealityRepository extends JpaRepository<RealityEntity, Long> {
    @Query(value = "SELECT * FROM REALITIES_TABLE  WHERE price >= :minPrice AND price <= :maxPrice",nativeQuery = true)
   List<RealityEntity> findRealityEntityByPriceInterval(Integer minPrice, Integer maxPrice);
    List<RealityEntity> findAllByOwnerId(Long ownerId);
}
