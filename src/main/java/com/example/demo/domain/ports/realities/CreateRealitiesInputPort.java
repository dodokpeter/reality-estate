package com.example.demo.domain.ports.realities;

import com.example.demo.domain.models.Reality;

public interface CreateRealitiesInputPort {
    Reality addReality(Reality reality);
}
