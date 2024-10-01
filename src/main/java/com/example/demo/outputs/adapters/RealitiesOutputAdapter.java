package com.example.demo.outputs.adapters;

import com.example.demo.reality.RealityRepository;
import com.example.demo.domain.models.Reality;
import com.example.demo.domain.ports.RealitiesOutputPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class RealitiesOutputAdapter implements RealitiesOutputPort {

    private final RealityRepository realityRepository;

    @Override
    public List<Reality> getRealities() {
        var realities = realityRepository.findAll();
        return realities.stream().map(realityEntity -> new Reality(realityEntity.getId(),
                                    realityEntity.getType(),
                                    realityEntity.getLocation(),
                                    realityEntity.getPrice(),
                                    realityEntity.getRooms(),
                                    realityEntity.getArea(),
                                    realityEntity.getDescription(), realityEntity.getMedias()))
                                    .toList();
    }
}
