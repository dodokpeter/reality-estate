package com.example.demo.outputs;

import com.example.demo.domain.models.Media;
import com.example.demo.domain.ports.CreateMediaOutputPort;
import com.example.demo.domain.ports.MediaOutputPort;
import com.example.demo.outputs.entities.MediaEntity;
import com.example.demo.outputs.mappers.MediaOutputMapper;
import com.example.demo.outputs.repositories.MediaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class MediaOutputAdapter implements MediaOutputPort, CreateMediaOutputPort {

    private final MediaRepository mediaRepository;

    @Override
    public List<Media> getMediaByRealityId(Long realityId) {
        List<MediaEntity> medias = mediaRepository.findAllById(realityId);
        return MediaOutputMapper.mapMediaEntityToMediaList(medias);
    }

    @Override
    public Media addMedia(Media media) {
        log.info("Adding a new reality to the database ...");
        MediaEntity mediaEntity = MediaOutputMapper.mapMediaToMediaEntity(media);
        MediaEntity saved = mediaRepository.save(mediaEntity);
        return MediaOutputMapper.mapMediaEntityToMedia(saved);
    }
}
