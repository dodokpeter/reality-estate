package com.example.demo.inputs.mappers;

import com.example.demo.domain.models.Media;
import com.example.demo.entities.MediaDTO;
import com.example.demo.domain.models.Reality;
import com.example.demo.inputs.models.RealityResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public interface RealityInputMapper {

    private static MediaDTO mapMedia(Media media) {
        return new MediaDTO(
                media.getUrl(),
                media.getType()
        );
    }

    private static List<MediaDTO> mapMedias(List<Media> medias) {
        return medias.stream()
                .map(RealityInputMapper::mapMedia)
                .toList();
    }

    static RealityResponse mapRealityToRealityResponse(Reality reality) {
        return new RealityResponse(
                reality.getId(),
                reality.getType(),
                reality.getLocation(),
                reality.getPrice(),
                reality.getRooms(),
                reality.getArea(),
                reality.getDescription(),
                mapMedias(reality.getMedias()),
                mapMedia(reality.getMedias().getFirst())
        );
    }

    static List<RealityResponse> mapRealityListToRealityResponseList(List<Reality> realityList) {
        return realityList.stream()
                .map(RealityInputMapper::mapRealityToRealityResponse)
                .collect(Collectors.toList());
    }

}
