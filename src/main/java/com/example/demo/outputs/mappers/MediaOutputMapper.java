package com.example.demo.outputs.mappers;

import com.example.demo.domain.models.Media;
import com.example.demo.outputs.entities.MediaEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
}
