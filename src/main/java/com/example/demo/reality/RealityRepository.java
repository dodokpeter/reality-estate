package com.example.demo.reality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealityRepository extends JpaRepository<Reality, Long> {

}
