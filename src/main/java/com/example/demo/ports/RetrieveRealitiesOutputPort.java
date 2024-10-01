package com.example.demo.ports;

import com.example.demo.inputs.models.RealityResponse;
import com.example.demo.reality.RealityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RetrieveRealitiesOutputPort {
    List<RealityResponse> getRealities();
    Page<RealityResponse> getPage(Pageable page) throws RealityNotFoundException;
    <T> ResponseEntity<T> getRealityById(Long realityId) throws RealityNotFoundException;
}
