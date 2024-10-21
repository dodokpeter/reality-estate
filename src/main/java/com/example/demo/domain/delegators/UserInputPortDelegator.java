package com.example.demo.domain.delegators;

import com.example.demo.domain.models.User;
import com.example.demo.domain.ports.user.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserInputPortDelegator implements UserInputPort, CreateUserInputPort, EditUserInputPort {

    private final UserOutputPort userOutputPort;
    private final CreateUserOutputPort createUserOutputPort;
    private final EditUserOutputPort editUserOutputPort;

    @Override
    public List<User> getUsers() {
        return userOutputPort.getUsers();
    }

    @Override
    public User createUser(User user) {
      return createUserOutputPort.createUser(user);
    }

    @Override
    public User addUserToReality(long userId, long realityId) {
        return editUserOutputPort.addUserToReality(userId, realityId);
    }
}


