package com.example.demo.domain.ports;

import com.example.demo.domain.models.Media;

public interface CreateMediaInputPort {
    Media addMedia(Media media, Long realityId);
}
