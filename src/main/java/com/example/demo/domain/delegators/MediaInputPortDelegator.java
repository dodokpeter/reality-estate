package com.example.demo.domain.delegators;

import com.example.demo.domain.exceptions.MediaNotFoundException;
import com.example.demo.domain.models.Media;
import com.example.demo.domain.models.Reality;
import com.example.demo.domain.ports.media.*;
import com.example.demo.domain.ports.realities.RealitiesOutputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MediaInputPortDelegator implements MediaInputPort, CreateMediaInputPort, UpdateMediaInputPort {

    private final MediaOutputPort mediaOutputPort;
    private final CreateMediaOutputPort createMediaOutputPort;
    private final UpdateMediaOutputPort updateMediaOutputPort;
    private final RealitiesOutputPort realitiesOutputPort;

    @Override
    public List<Media> getMediaByRealityId(Long realityId) {
        return mediaOutputPort.getMediaByRealityId(realityId);
    }

    @Override
    public Media getMediaById(Long id) {
        return mediaOutputPort.getMediaById(id);
    }

    @Override
    public Media addMedia(Media media, Long realityId) {
        Reality realityById = realitiesOutputPort.getRealityById(realityId);
        media.setReality(realityById);
        return createMediaOutputPort.addMedia(media);
    }

    @Override
    public Media updateMedia(Media media, Long mediaId) throws MediaNotFoundException {
        return updateMediaOutputPort.updateMedia(media, mediaId);
    }
}

