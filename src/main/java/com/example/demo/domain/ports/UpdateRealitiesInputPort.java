package com.example.demo.domain.ports;

import com.example.demo.domain.exceptions.RealityNotFoundException;
import com.example.demo.domain.models.Reality;

public interface UpdateRealitiesInputPort {
    Reality updateReality(Reality reality, Long realityId) throws RealityNotFoundException;
}
