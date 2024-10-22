package com.example.demo.domain.ports.realities;

import com.example.demo.domain.exceptions.RealityNotFoundException;
import com.example.demo.domain.models.Reality;
import com.example.demo.domain.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RealitiesInputPort {
    List<Reality> getRealities();
    Page<Reality> getRealitiesByPage(Pageable page);
    Reality getRealityById(Long id);
    User getOwnerOfReality(Long realityId) throws RealityNotFoundException;
}
