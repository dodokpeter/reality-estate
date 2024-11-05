package com.example.demo.domain.ports.realities;

import com.example.demo.domain.models.Reality;

public interface UpdateRealitiesOutputPort {
    Reality updateReality(Reality reality, Reality realityFetched);
    Reality updateReality(Reality reality);
}
