package com.example.demo.outputs.mappers;

import com.example.demo.domain.models.Reality;
import com.example.demo.outputs.entities.RealityEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RealityOutputMapper {
    Reality mapRealityEntityToReality(RealityEntity realityEntity);
    RealityEntity mapRealityToRealityEntity(Reality reality);
    List<Reality> mapRealityEntityListToRealityList(List<RealityEntity> realities);
}
