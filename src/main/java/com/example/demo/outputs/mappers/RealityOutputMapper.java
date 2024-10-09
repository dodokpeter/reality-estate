package com.example.demo.outputs.mappers;

import com.example.demo.domain.models.Reality;
import com.example.demo.outputs.entities.RealityEntity;

import java.util.List;

public class RealityOutputMapper {

    public static Reality mapRealityEntityToReality(RealityEntity realityEntity) {
        return new Reality(realityEntity.getId(),
                realityEntity.getType(),
                realityEntity.getLocation(),
                realityEntity.getPrice(),
                realityEntity.getRooms(),
                realityEntity.getArea(),
                realityEntity.getDescription(),
                MediaOutputMapper.mapMediaEntityToMediaList(realityEntity.getMedias())
        );
    }

    public static RealityEntity mapRealityToRealityEntity(Reality reality) {
        return new RealityEntity(
                reality.getId(),
                reality.getType(),
                reality.getLocation(),
                reality.getPrice(),
                reality.getRooms(),
                reality.getArea(),
                reality.getDescription(),
                MediaOutputMapper.mapMediaListToMediaEntityList(reality.getMedias())
        );
    }

    public static List<Reality> mapRealityEntityListToRealityList(List<RealityEntity> realities) {
        return realities.stream().map(RealityOutputMapper::mapRealityEntityToReality)
                .toList();
    }
}
