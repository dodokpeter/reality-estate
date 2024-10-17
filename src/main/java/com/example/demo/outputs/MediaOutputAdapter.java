package com.example.demo.outputs;

import com.example.demo.domain.exceptions.MediaNotFoundException;
import com.example.demo.domain.models.Media;
import com.example.demo.domain.models.MediaType;
import com.example.demo.domain.ports.media.CreateMediaOutputPort;
import com.example.demo.domain.ports.media.MediaOutputPort;
import com.example.demo.domain.ports.media.UpdateMediaOutputPort;
import com.example.demo.outputs.entities.MediaEntity;
import com.example.demo.outputs.entities.RealityEntity;
import com.example.demo.outputs.mappers.MediaOutputMapper;
import com.example.demo.outputs.mappers.RealityOutputMapper;
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

    @Override
    public List<Media> getMediaByRealityId(Long realityId) {
        List<MediaEntity> medias = mediaRepository.findAllByRealityEntityId(realityId);
        return MediaOutputMapper.mapMediaEntityToMediaList(medias);
    }

    @Override
    public Media getMediaById(Long mediaId) {
        Optional<MediaEntity> media = mediaRepository.findById(mediaId);
        if (media.isPresent()) {
            return MediaOutputMapper.mapMediaEntityToMedia(media.get());
        } else
            return null;
    }

    @Override
    public List<Media> getMediaByRealityIdAndMediaType(Long realityId, MediaType mediaType) {
        List<MediaEntity> medias = mediaRepository.findByRealityEntityIdAndMediaType(realityId, mediaType);
        return MediaOutputMapper.mapMediaEntityToMediaList(medias);
    }

    @Override
    public Media addMedia(Media media) {
        log.info("Adding a new reality to the database ...");
        MediaEntity mediaEntity = MediaOutputMapper.mapMediaToMediaEntity(media);
        RealityEntity realityEntity = RealityOutputMapper.mapRealityToRealityEntity(media.getReality());
        mediaEntity.setRealityEntity(realityEntity);
        MediaEntity saved = mediaRepository.save(mediaEntity);
        return MediaOutputMapper.mapMediaEntityToMedia(saved);
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
            return MediaOutputMapper.mapMediaEntityToMedia(mediaInDb);
        }
        else {
            log.error("Could not find media with this id.");
            throw new MediaNotFoundException("Položka Media s daným realityId nebola nájdená.");
        }
    }

    @Override
    public void deleteMediaById(Long mediaId) {
        mediaRepository.deleteById(mediaId);
    }


}
