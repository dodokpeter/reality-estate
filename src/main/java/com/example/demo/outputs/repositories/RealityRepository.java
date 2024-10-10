package com.example.demo.outputs.repositories;

import com.example.demo.outputs.entities.RealityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// todo 5: diff between jpa and crud ?
@Repository
public interface RealityRepository extends JpaRepository<RealityEntity, Long> {

}
