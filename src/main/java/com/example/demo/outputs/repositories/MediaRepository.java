package com.example.demo.outputs.repositories;

import com.example.demo.domain.models.Media;
import com.example.demo.outputs.entities.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<MediaEntity, Long> {
    List<MediaEntity> findAllById(Long id);
}
