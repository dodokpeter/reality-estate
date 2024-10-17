package com.example.demo.domain.ports.media;

import com.example.demo.domain.models.Media;
import com.example.demo.domain.models.MediaType;

import java.util.List;
import java.util.Optional;

public interface MediaOutputPort {
    List <Media> getMediaByRealityId(Long realityId);
    Media getMediaById(Long id);
    void deleteMediaById(Long id);
    List <Media> getMediaByRealityIdAndMediaType(Long realityId, MediaType mediaType);
}
