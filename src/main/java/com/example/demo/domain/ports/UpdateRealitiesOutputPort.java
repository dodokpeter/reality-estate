package com.example.demo.domain.ports;

import com.example.demo.domain.models.Reality;
import com.example.demo.reality.RealityNotFoundException;

public interface UpdateRealitiesOutputPort {
    void updateReality(Reality reality, Long realityId) throws RealityNotFoundException;
}
