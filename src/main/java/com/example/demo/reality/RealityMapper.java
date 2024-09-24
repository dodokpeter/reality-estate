package com.example.demo.reality;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RealityMapper {

    private List<MediaDTO> mapMedias(List<Media> medias) {
        return medias.stream()
                .map(m -> new MediaDTO(m.getUrl(), m.getType()))
                .toList();
    }

    RealityDTO manualMapper(Reality reality) {
        return new RealityDTO(
                reality.getId(),
                reality.getType(),
                reality.getLocation(),
                reality.getPrice(),
                reality.getRooms(),
                reality.getArea(),
                reality.getDescription(),
                mapMedias(reality.getMedias())
        );
    }

    List<RealityDTO> manualListMapper(List<Reality> realityList) {
        return realityList.stream()
                .map(r -> new RealityDTO(
                        r.getId(),
                        r.getType(),
                        r.getLocation(),
                        r.getPrice(),
                        r.getRooms(),
                        r.getArea(),
                        r.getDescription(),
                        mapMedias(r.getMedias())
                ))
                .collect(Collectors.toList());
    }

    // todo: another method for that / diff class (realityMapper for all the mappers) -> mapstruct

}
