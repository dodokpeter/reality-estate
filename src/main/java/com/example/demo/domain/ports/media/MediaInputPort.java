package com.example.demo.domain.ports.media;

import com.example.demo.domain.models.Media;

import java.util.List;


public interface MediaInputPort {
    List<Media> getMediaByRealityId(Long realityId);
    Media getMediaById(Long mediaId);
    void deleteMediaById(Long mediaId);
}
