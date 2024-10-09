package com.example.demo.domain.delegators;

import com.example.demo.domain.models.Media;
import com.example.demo.domain.ports.CreateMediaInputPort;
import com.example.demo.domain.ports.CreateMediaOutputPort;
import com.example.demo.domain.ports.MediaInputPort;
import com.example.demo.domain.ports.MediaOutputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

// todo: remain to MediaInputPortDelegator (for consistency)
@Service
@AllArgsConstructor
@Slf4j
public class MediaInputDelegator implements MediaInputPort, CreateMediaInputPort {

    private final MediaOutputPort mediaOutputPort;
    private final CreateMediaOutputPort createMediaOutputPort;

    @Override
    public List<Media> getMediaByRealityId(Long realityId) {
        return mediaOutputPort.getMediaByRealityId(realityId);
    }

    @Override
    public Media addMedia(Media media) {
        return createMediaOutputPort.addMedia(media);
    }
}

