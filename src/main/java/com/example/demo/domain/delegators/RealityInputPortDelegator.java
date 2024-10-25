package com.example.demo.domain.delegators;

import com.example.demo.domain.exceptions.RealityNotFoundException;
import com.example.demo.domain.exceptions.UserNotFoundException;
import com.example.demo.domain.models.Reality;
import com.example.demo.domain.models.User;
import com.example.demo.domain.ports.realities.*;
import com.example.demo.domain.ports.user.UserOutputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RealityInputPortDelegator implements RealitiesInputPort, CreateRealitiesInputPort, UpdateRealitiesInputPort {

    private final RealitiesOutputPort realitiesOutputPort;
    private final CreateRealitiesOutputPort createRealitiesOutputPort;
    private final UpdateRealitiesOutputPort updateRealitiesOutputPort;

    private final UserOutputPort userOutputPort;


    @Override
    public List<Reality> getRealities() {
        var realities = realitiesOutputPort.getRealities();
        return realities;
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
    public List<Reality> getRealitiesByOwner(Long userId) {
        userOutputPort.existsById(userId);  // will throw an exception if the user doesn't exist
        return realitiesOutputPort.getRealitiesByOwner(userId);
    }

    @Override
    public Reality addReality(Reality reality) {
        return createRealitiesOutputPort.addReality(reality);
    }

    @Override
    public Reality updateReality(Reality reality, Long realityId) throws RealityNotFoundException {
        return updateRealitiesOutputPort.updateReality(reality, realityId);
    }

    @Override
    public Reality assignUser(Long userId, Long realityId) throws UserNotFoundException {
        User user = userOutputPort.getUserById(userId);
        Reality reality = realitiesOutputPort.getRealityById(realityId);
        reality.setOwner(user);
        return updateRealitiesOutputPort.updateReality(reality);
    }
}
