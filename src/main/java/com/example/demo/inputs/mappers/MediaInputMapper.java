package com.example.demo.inputs.mappers;

import com.example.demo.domain.models.Media;
import com.example.demo.inputs.models.MediaResponse;

import java.util.List;

public interface MediaInputMapper {

   static MediaResponse mapMediaToMediaResponse(Media media) {
        return new MediaResponse(
                media.getId(),
                media.getUrl(),
                media.getType());
   }

   static List <MediaResponse> mapMediaToMediaResponse(List<Media> medias) {
        return medias.stream().map(MediaInputMapper::mapMediaToMediaResponse).toList();
   }
}
