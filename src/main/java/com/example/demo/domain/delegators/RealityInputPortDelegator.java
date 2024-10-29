package com.example.demo.domain.delegators;

import com.example.demo.domain.exceptions.MissingPermissionsException;
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
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class RealityInputPortDelegator implements RealitiesInputPort, CreateRealitiesInputPort, UpdateRealitiesInputPort {

    private final RealitiesOutputPort realitiesOutputPort;
    private final CreateRealitiesOutputPort createRealitiesOutputPort;
    private final UpdateRealitiesOutputPort updateRealitiesOutputPort;

    private final UserOutputPort userOutputPort;


    @Override
    public List<Reality> getRealities(Integer minPrice, Integer maxPrice) {
        var realities = realitiesOutputPort.getRealities(minPrice, maxPrice);
        return realities;
    }

    @Override
    public Page<Reality> getRealitiesByPage(Pageable page) {
        log.info("Returning the list of PAGINATED realities ...");
        return realitiesOutputPort.getRealitiesByPage(page);
    }

    @Override
    public Reality getRealityById(Long id) throws RealityNotFoundException {
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
    public Reality updateReality(Reality reality, Long realityId, Long userId) throws RealityNotFoundException {
        // need to check the owner from the db (as the data from the request aren't really reliable)
        Reality realityFetched = realitiesOutputPort.getRealityById(realityId);

        // check if the user changing the reality is the user sending the request
        if (!Objects.equals(realityFetched.getOwner().getId(), userId)) {
            throw new MissingPermissionsException("This user cannot modify this reality");
        }

        return updateRealitiesOutputPort.updateReality(reality, realityFetched);
    }

    @Override
    public Reality assignUser(Long userId, Long realityId) throws UserNotFoundException, RealityNotFoundException {
        User user = userOutputPort.getUserById(userId);
        Reality reality = realitiesOutputPort.getRealityById(realityId);
        reality.setOwner(user);
        return updateRealitiesOutputPort.updateReality(reality);
    }
}
