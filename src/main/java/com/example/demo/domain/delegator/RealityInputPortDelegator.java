package com.example.demo.domain.delegator;

import com.example.demo.domain.models.Reality;
import com.example.demo.domain.ports.RealitiesInputPort;
import com.example.demo.domain.ports.RealitiesOutputPort;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class RealityInputPortDelegator implements RealitiesInputPort {

    private RealitiesOutputPort realitiesOutputPort;


    @Override
    public List<Reality> getRealities() {
        return realitiesOutputPort.getRealities();
    }
}
