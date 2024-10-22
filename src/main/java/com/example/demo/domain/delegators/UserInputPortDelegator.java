package com.example.demo.domain.delegators;

import com.example.demo.domain.exceptions.RealityNotFoundException;
import com.example.demo.domain.exceptions.UserNotFoundException;
import com.example.demo.domain.models.Reality;
import com.example.demo.domain.models.User;
import com.example.demo.domain.ports.realities.RealitiesOutputPort;
import com.example.demo.domain.ports.user.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserInputPortDelegator implements UserInputPort, CreateUserInputPort, AssignRealityToUserInputPort {

    private final UserOutputPort userOutputPort;
    private final CreateUserOutputPort createUserOutputPort;
    private final AssignRealityToUserOutputPort assignRealityToUserOutputPort;
    private final RealitiesOutputPort realitiesOutputPort;;

    @Override
    public List<User> getUsers() {
        return userOutputPort.getUsers();
    }

    @Override
    public User getUserById(Long userId) throws UserNotFoundException {
        return userOutputPort.getUserById(userId);
    }

    @Override
    public User createUser(User user) {
      return createUserOutputPort.createUser(user);
    }

    @Override
    public User getOwner(Long realityId) throws RealityNotFoundException {
        return userOutputPort.getOwner(realityId);
    }

    @Override
    public User assign(Long userId, Long realityId) throws UserNotFoundException {
        // todo: delegate the task to output portS -> getUserById, getRealityById
        // (split the userOutputAdapter functionality)
        User user = getUserById(userId);
        Reality reality = realitiesOutputPort.getRealityById(realityId);
        return assignRealityToUserOutputPort.assign(user, reality);
    }
}


