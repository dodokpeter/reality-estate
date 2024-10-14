package com.example.demo.inputs.mappers;

import com.example.demo.domain.models.Media;
import com.example.demo.inputs.models.MediaResponse;

import java.util.List;
import java.util.Optional;

public class MediaInputMapper {

   public static MediaResponse mapMediaToMediaResponse(Media media) {
        return new MediaResponse(
                media.getId(),
                media.getUrl(),
                media.getType());
   }

   public static List <MediaResponse> mapMediaToMediaResponse(List<Media> medias) {
        return medias.stream().map(MediaInputMapper::mapMediaToMediaResponse).toList();
   }

//    public static MediaResponse mapMediumToMediaResponse(Media medium) {
//        return new MediaResponse(
//                medium.getId(),
//                medium.getUrl(),
//                medium.getType());
//    }
//    public static MediaResponse mapMediumToMediaResponse(Optional<Media> medium) {
//        return medium.stream().map(MediaInputMapper.mapMediumToMediaResponse(medium);
//    }
}
