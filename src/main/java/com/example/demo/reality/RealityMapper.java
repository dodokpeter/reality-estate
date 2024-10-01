package com.example.demo.reality;

import com.example.demo.entities.Media;
import com.example.demo.entities.MediaDTO;
import com.example.demo.entities.Reality;
import com.example.demo.entities.RealityDTO;
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

    static RealityDTO manualMapper(Reality reality) {
        return new RealityDTO(
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

    static List<RealityDTO> manualListMapper(List<Reality> realityList) {
        return realityList.stream()
                .map(r -> new RealityDTO(
                        r.getId(),
                        r.getType(),
                        r.getLocation(),
                        r.getPrice(),
                        r.getRooms(),
                        r.getArea(),
                        r.getDescription(),
                        mapMedias(r.getMedias()),
                        mapMedia(r.getMedias().getFirst())
                ))
                .collect(Collectors.toList());
    }
}
