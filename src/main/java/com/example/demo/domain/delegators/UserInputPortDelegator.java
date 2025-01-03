package com.example.demo.domain.delegators;

import com.example.demo.domain.exceptions.RealityNotFoundException;
import com.example.demo.domain.exceptions.UserNotFoundException;
import com.example.demo.domain.models.User;
import com.example.demo.domain.ports.user.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserInputPortDelegator implements UserInputPort, CreateUserInputPort {

    private final UserOutputPort userOutputPort;
    private final CreateUserOutputPort createUserOutputPort;

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
}


