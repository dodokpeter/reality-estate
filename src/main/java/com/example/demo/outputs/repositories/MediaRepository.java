package com.example.demo.outputs.repositories;

import com.example.demo.domain.models.MediaType;
import com.example.demo.outputs.entities.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MediaRepository extends JpaRepository<MediaEntity, Long> {
    List<MediaEntity> findAllByRealityEntityId(Long id);
    Optional<MediaEntity> findById(Long id);
    void deleteById(Long id);
    List<MediaEntity> findByRealityEntityIdAndMediaType(Long id, MediaType mediaType);
}
