package com.example.demo.outputs.adapters;

import com.example.demo.outputs.adapters.entities.RealityEntity;
import com.example.demo.reality.RealityMapper;
import com.example.demo.reality.RealityRepository;
import com.example.demo.domain.models.Reality;
import com.example.demo.domain.ports.RealitiesOutputPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@AllArgsConstructor
public class RealitiesOutputAdapter implements RealitiesOutputPort {

    private final RealityRepository realityRepository;


    @Override
    public List<Reality> getRealities() {
        var realities = realityRepository.findAll();
        return RealityMapper.mapRealityEntityListToRealityList(realities);
    }

    @Override
    public Page<Reality> getRealitiesByPage(Pageable page) {
        Page<RealityEntity> realities = realityRepository.findAll(page);
        List<RealityEntity> realityEntityList = realities.getContent();
        List<Reality> realityList = RealityMapper.mapRealityEntityListToRealityList(realityEntityList);
        return new PageImpl<>(realityList, page, realityList.size());
    }
}
