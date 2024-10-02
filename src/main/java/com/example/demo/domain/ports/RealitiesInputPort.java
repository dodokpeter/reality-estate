package com.example.demo.domain.ports;

import com.example.demo.domain.models.Reality;
import com.example.demo.inputs.models.RealityResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RealitiesInputPort {
    List<Reality> getRealities();
    Page<RealityResponse> getRealitiesByPage(Pageable page);
    Reality getRealityById(Long id);

}
