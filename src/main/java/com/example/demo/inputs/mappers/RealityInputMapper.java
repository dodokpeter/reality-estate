package com.example.demo.inputs.mappers;

import com.example.demo.domain.models.Reality;
import com.example.demo.inputs.models.RealityResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RealityInputMapper {
    RealityResponse mapRealityToRealityResponse(Reality reality);
    List<RealityResponse> mapRealityListToRealityResponseList(List<Reality> realityList);
}
