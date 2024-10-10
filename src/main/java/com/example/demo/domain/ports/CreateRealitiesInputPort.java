package com.example.demo.domain.ports;

import com.example.demo.domain.models.Reality;

public interface CreateRealitiesInputPort {
    Reality addReality(Reality reality);
}
