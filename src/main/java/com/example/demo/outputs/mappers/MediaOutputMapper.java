package com.example.demo.outputs.mappers;

import com.example.demo.domain.models.Media;
import com.example.demo.outputs.entities.MediaEntity;

import java.util.List;
import java.util.Optional;

public class MediaOutputMapper {

  public static Media mapMediaEntityToMedia(MediaEntity mediaEntity) {
        return new Media(
                mediaEntity.getId(),
                mediaEntity.getUrl(),
                mediaEntity.getMediaType()
        );
    }

    public static List<Media> mapMediaEntityToMediaList(List<MediaEntity> medias) {
        return medias.stream().map(MediaOutputMapper::mapMediaEntityToMedia).toList();
    }

    public static MediaEntity mapMediaToMediaEntity(Media media) {
        return new MediaEntity(
                media.getId(),
                media.getUrl(),
                media.getType()
        );
    }

    public static List<MediaEntity> mapMediaListToMediaEntityList(List<Media> medias) {
        return medias.stream().map(MediaOutputMapper::mapMediaToMediaEntity).toList();
    }
}
