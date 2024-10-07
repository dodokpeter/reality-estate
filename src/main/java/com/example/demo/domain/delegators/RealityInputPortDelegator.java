package com.example.demo.domain.delegators;

import com.example.demo.domain.exceptions.RealityNotFoundException;
import com.example.demo.domain.models.Reality;
import com.example.demo.domain.ports.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class RealityInputPortDelegator implements RealitiesInputPort, CreateRealitiesInputPort, UpdateRealitiesInputPort {

    private RealitiesOutputPort realitiesOutputPort;
    private CreateRealitiesOutputPort createRealitiesOutputPort;
    private UpdateRealitiesOutputPort updateRealitiesOutputPort;


    @Override
    public List<Reality> getRealities() {
        return realitiesOutputPort.getRealities();
    }

    @Override
    public Page<Reality> getRealitiesByPage(Pageable page) {
        log.info("Returning the list of PAGINATED realities ...");
        return realitiesOutputPort.getRealitiesByPage(page);
    }

    @Override
    public Reality getRealityById(Long id) {
        return realitiesOutputPort.getRealityById(id);
    }

    @Override
    public Reality addReality(Reality reality) {
        return createRealitiesOutputPort.addReality(reality);
    }

    @Override
    public void updateReality(Reality reality, Long realityId) throws RealityNotFoundException {
        updateRealitiesOutputPort.updateReality(reality, realityId);
    }
}
