package com.example.demo.domain.ports.media;

import com.example.demo.domain.exceptions.MediaNotFoundException;
import com.example.demo.domain.models.Media;

public interface UpdateMediaOutputPort {
    Media updateMedia(Media media, Long mediaId) throws MediaNotFoundException;
}
