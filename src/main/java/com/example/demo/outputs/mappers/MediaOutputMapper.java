package com.example.demo.outputs.mappers;

import com.example.demo.domain.models.Media;
import com.example.demo.outputs.entities.MediaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface MediaOutputMapper {
    Media mapMediaEntityToMedia(MediaEntity mediaEntity);
    List<Media> mapMediaEntityToMediaList(List<MediaEntity> medias);

    @Mapping(target = "mediaType", source = "media.type")
    @Mapping(target = "realityEntity", source = "media.reality")
    MediaEntity mapMediaToMediaEntity(Media media);
    List<MediaEntity> mapMediaListToMediaEntityList(List<Media> medias);
}
