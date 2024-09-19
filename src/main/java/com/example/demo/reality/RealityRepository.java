package com.example.demo.reality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// todo 5: diff between jpa and crud ?
@Repository
public interface RealityRepository extends JpaRepository<Reality, Long> {

}
