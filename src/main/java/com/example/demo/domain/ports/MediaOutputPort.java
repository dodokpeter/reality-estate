package com.example.demo.domain.ports;

import com.example.demo.domain.models.Media;

import java.util.List;

public interface MediaOutputPort {
    List <Media> getMediaByRealityId(Long realityId);
}
