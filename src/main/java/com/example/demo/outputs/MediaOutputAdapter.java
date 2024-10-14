package com.example.demo.outputs;

import com.example.demo.domain.models.Media;
import com.example.demo.domain.ports.MediaOutputPort;
import com.example.demo.outputs.entities.MediaEntity;
import com.example.demo.outputs.mappers.MediaOutputMapper;
import com.example.demo.outputs.repositories.MediaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class MediaOutputAdapter implements MediaOutputPort {

    private final MediaRepository mediaRepository;

    @Override
    public List<Media> getMediaByRealityId(Long realityId) {
        List<MediaEntity> medias = mediaRepository.findAllById(realityId);
        return MediaOutputMapper.mapMediaEntityToMediaList(medias);
    }

    @Override
    public Optional<Media> getMediaById(Long id) {
//        Optional<MediaEntity> media = mediaRepository.findById(id);
//        return  MediaOutputMapper.mapMediaEntityToMedia(media);
        return mediaRepository.findById(id).map(MediaOutputMapper::mapMediaEntityToMedia);
    }

}
