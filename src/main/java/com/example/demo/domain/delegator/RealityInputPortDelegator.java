package com.example.demo.domain.delegator;

import com.example.demo.domain.models.Reality;
import com.example.demo.domain.ports.CreateRealitiesInputPort;
import com.example.demo.domain.ports.RealitiesInputPort;
import com.example.demo.domain.ports.RealitiesOutputPort;
import com.example.demo.domain.ports.UpdateRealitiesInputPort;
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
    private CreateRealitiesInputPort createRealitiesOutputPort;
    private UpdateRealitiesInputPort updateRealitiesInputPort;


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
        Reality reality = realitiesOutputPort.getRealityById(id);
        return reality;

    }

    @Override
    public void addReality(Reality reality) {
        createRealitiesOutputPort.addReality(reality);
    }

    @Override
    public void updateReality(Reality reality, Long realityId) {
        updateRealitiesInputPort.updateReality(reality, realityId);
    }
}
