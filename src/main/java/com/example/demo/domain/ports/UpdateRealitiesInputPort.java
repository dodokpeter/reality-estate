package com.example.demo.domain.ports;

import com.example.demo.domain.models.Reality;

public interface UpdateRealitiesInputPort {
    void updateReality(Reality reality, Long realityId);
}
