package com.example.demo.domain.ports.media;

import com.example.demo.domain.models.Media;

import java.util.List;
import java.util.Optional;

public interface MediaOutputPort {
    List <Media> getMediaByRealityId(Long realityId);
    Media getMediaById(Long id);
    void deleteMediaById(Long id);
}
