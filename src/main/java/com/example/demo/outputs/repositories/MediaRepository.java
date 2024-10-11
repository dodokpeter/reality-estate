package com.example.demo.outputs.repositories;

import com.example.demo.outputs.entities.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MediaRepository extends JpaRepository<MediaEntity, Long> {
    List<MediaEntity> findAllById(Long id);

    MediaEntity findAllById(Long id);


}
