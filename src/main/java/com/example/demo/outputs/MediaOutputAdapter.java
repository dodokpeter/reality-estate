package com.example.demo.outputs;

import com.example.demo.domain.exceptions.MediaNotFoundException;
import com.example.demo.domain.models.Media;
import com.example.demo.domain.ports.media.CreateMediaOutputPort;
import com.example.demo.domain.ports.media.MediaOutputPort;
import com.example.demo.domain.ports.media.UpdateMediaOutputPort;
import com.example.demo.outputs.entities.MediaEntity;
import com.example.demo.outputs.entities.RealityEntity;
import com.example.demo.outputs.mappers.OutputMapper;
import com.example.demo.outputs.repositories.MediaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class MediaOutputAdapter implements MediaOutputPort, CreateMediaOutputPort, UpdateMediaOutputPort {

    private final MediaRepository mediaRepository;
    private final OutputMapper outputMapper;

    @Override
    public List<Media> getMediaByRealityId(Long realityId) {
        List<MediaEntity> medias = mediaRepository.findAllById(realityId);
        return outputMapper.mapMediaEntityToMediaList(medias);
    }

    @Override
    public Media addMedia(Media media) {
        log.info("Adding a new reality to the database ...");
        MediaEntity mediaEntity = outputMapper.mapMediaToMediaEntity(media);
        RealityEntity realityEntity = outputMapper.mapRealityToRealityEntity(media.getReality());
        mediaEntity.setRealityEntity(realityEntity);
        MediaEntity saved = mediaRepository.save(mediaEntity);
        return outputMapper.mapMediaEntityToMedia(saved);
    }

    @Override
    public Media updateMedia(Media media, Long mediaId) throws MediaNotFoundException {
        Optional<MediaEntity> mediaInDbOpt = mediaRepository.findById(mediaId);
        if (mediaInDbOpt.isPresent()) {
            MediaEntity mediaInDb = mediaInDbOpt.get();
            mediaInDb.setId(mediaId);
            mediaInDb.setMediaType(media.getType());
            mediaInDb.setUrl(media.getUrl());
            mediaInDb = mediaRepository.save(mediaInDb);
            log.info("Updated the media with the current id.");
            return outputMapper.mapMediaEntityToMedia(mediaInDb);
        }
        else {
            log.error("Could not find media with this id.");
            throw new MediaNotFoundException("Položka Media s daným realityId nebola nájdená.");
        }
    }
}
