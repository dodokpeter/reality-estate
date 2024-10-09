package com.example.demo.inputs.mappers;

import com.example.demo.domain.models.Reality;
import com.example.demo.inputs.models.RealityResponse;

import java.util.List;
import java.util.stream.Collectors;

public class RealityInputMapper {

    public static RealityResponse mapRealityToRealityResponse(Reality reality) {
        return new RealityResponse(
                reality.getId(),
                reality.getType(),
                reality.getLocation(),
                reality.getPrice(),
                reality.getRooms(),
                reality.getArea(),
                reality.getDescription(),
                MediaInputMapper.mapMediaToMediaResponse(reality.getMedias())
        );
    }

    public static List<RealityResponse> mapRealityListToRealityResponseList(List<Reality> realityList) {
        return realityList.stream()
                .map(RealityInputMapper::mapRealityToRealityResponse)
                .collect(Collectors.toList());
    }
}
