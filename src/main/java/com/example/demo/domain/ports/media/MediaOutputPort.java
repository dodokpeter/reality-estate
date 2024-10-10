package com.example.demo.domain.ports.media;

import com.example.demo.domain.models.Media;

import java.util.List;

public interface MediaOutputPort {
    List <Media> getMediaByRealityId(Long realityId);
}
