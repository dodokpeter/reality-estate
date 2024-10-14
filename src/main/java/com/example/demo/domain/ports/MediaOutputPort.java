package com.example.demo.domain.ports;

import com.example.demo.domain.models.Media;

import java.util.List;
import java.util.Optional;

public interface MediaOutputPort {
    List <Media> getMediaByRealityId(Long realityId);
    Optional<Media> getMediaById(Long id);
}
