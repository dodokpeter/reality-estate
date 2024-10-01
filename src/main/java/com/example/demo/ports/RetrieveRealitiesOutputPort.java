package com.example.demo.ports;

import com.example.demo.entities.RealityDTO;
import com.example.demo.reality.RealityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RetrieveRealitiesOutputPort {
    List<RealityDTO> getRealities();
    Page<RealityDTO> getPage(Pageable page) throws RealityNotFoundException;
    <T> ResponseEntity<T> getRealityById(Long realityId) throws RealityNotFoundException;
}
