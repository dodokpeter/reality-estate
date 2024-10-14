package com.example.demo.domain.ports;

import com.example.demo.domain.models.Media;

import java.util.List;


public interface MediaInputPort {
    List<Media> getMediaByRealityId(Long realityId);
    Media getMediaById(Long mediaId);
}
