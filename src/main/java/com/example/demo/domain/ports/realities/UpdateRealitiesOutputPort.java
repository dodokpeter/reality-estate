package com.example.demo.domain.ports.realities;

import com.example.demo.domain.models.Reality;
import com.example.demo.domain.exceptions.RealityNotFoundException;

public interface UpdateRealitiesOutputPort {
    Reality updateReality(Reality reality, Long realityId) throws RealityNotFoundException;
}
