package com.example.demo.ports;

import com.example.demo.entities.Reality;
import com.example.demo.reality.RealityNotFoundException;

public interface EditRealities {
    void addReality(Reality reality);
    void updateReality(Reality reality, Long realityId) throws RealityNotFoundException;
}
