package com.example.demo.domain.ports.realities;

import com.example.demo.domain.exceptions.RealityNotFoundException;
import com.example.demo.domain.models.Reality;

public interface UpdateRealitiesInputPort {
    Reality updateReality(Reality reality, Long realityId, Long userId) throws RealityNotFoundException;
    Reality assignUser(Long userId, Long realityId) throws RealityNotFoundException;
}
