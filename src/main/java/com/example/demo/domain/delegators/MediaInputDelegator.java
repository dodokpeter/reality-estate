package com.example.demo.domain.delegators;

import com.example.demo.domain.models.Media;
import com.example.demo.domain.ports.media.MediaInputPort;
import com.example.demo.domain.ports.media.MediaOutputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MediaInputDelegator implements MediaInputPort {

    private final MediaOutputPort mediaOutputPort;

    @Override
    public List<Media> getMediaByRealityId(Long realityId) {
        return mediaOutputPort.getMediaByRealityId(realityId);
    }

    @Override
    public Media getMediaById(Long id) {
        return mediaOutputPort.getMediaById(id);
    }

    @Override
    public void deleteMediaById(Long id) {
         mediaOutputPort.deleteMediaById(id);
    }
}

