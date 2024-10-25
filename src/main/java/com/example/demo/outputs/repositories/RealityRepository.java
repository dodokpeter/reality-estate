package com.example.demo.outputs.repositories;

import com.example.demo.outputs.entities.RealityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealityRepository extends JpaRepository<RealityEntity, Long> {
    List<RealityEntity> findAllByOwnerId(Long ownerId);
}
