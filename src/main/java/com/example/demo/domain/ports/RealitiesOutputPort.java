package com.example.demo.domain.ports;

import com.example.demo.domain.models.Reality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RealitiesOutputPort {
    List<Reality> getRealities();

    Page<Reality> getRealitiesByPage(Pageable page);

    Reality getRealityById(Long id);
}
