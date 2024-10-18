package com.example.demo.inputs.mappers;

import com.example.demo.domain.models.Media;
import com.example.demo.inputs.models.MediaResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MediaInputMapper {
    MediaResponse mapMediaToMediaResponse(Media media);
    List<MediaResponse> mapMediaToMediaResponse(List<Media> medias);
}
