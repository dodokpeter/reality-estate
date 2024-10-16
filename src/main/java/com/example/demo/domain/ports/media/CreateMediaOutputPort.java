package com.example.demo.domain.ports.media;

import com.example.demo.domain.models.Media;

public interface CreateMediaOutputPort {
    Media addMedia(Media media);
}
