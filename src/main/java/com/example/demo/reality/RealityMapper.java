package com.example.demo.reality;

import com.example.demo.entities.Media;
import com.example.demo.entities.MediaDTO;
import com.example.demo.domain.models.Reality;
import com.example.demo.inputs.models.RealityResponse;
import com.example.demo.outputs.adapters.entities.RealityEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public interface RealityMapper {

    private static MediaDTO mapMedia(Media media) {
        return new MediaDTO(
                media.getUrl(),
                media.getType()
        );
    }

    private static List<MediaDTO> mapMedias(List<Media> medias) {
        return medias.stream()
                .map(RealityMapper::mapMedia)
                .toList();
    }

    static Reality mapRealityEntityToReality(RealityEntity realityEntity) {
        return new Reality(realityEntity.getId(),
                realityEntity.getType(),
                realityEntity.getLocation(),
                realityEntity.getPrice(),
                realityEntity.getRooms(),
                realityEntity.getArea(),
                realityEntity.getDescription(), realityEntity.getMedias());
    }

    static List<Reality> mapRealityEntityListToRealityList(List<RealityEntity> realities) {
        return realities.stream().map(RealityMapper::mapRealityEntityToReality)
                .toList();
    }

    private static RealityResponse realityEntityToRealityResponse(RealityEntity reality) {
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

    static List<RealityResponse> mapRealityEntityListToRealityResponseList(List<RealityEntity> realities) {
        return realities.stream().map(RealityMapper::realityEntityToRealityResponse).toList();
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
                .map(RealityMapper::mapRealityToRealityResponse)
                .collect(Collectors.toList());
    }
}
