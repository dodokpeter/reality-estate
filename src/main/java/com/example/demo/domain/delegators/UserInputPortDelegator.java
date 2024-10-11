package com.example.demo.domain.delegators;

import com.example.demo.domain.models.User;
import com.example.demo.domain.ports.user.AddUserInputPort;
import com.example.demo.domain.ports.user.AddUserOutputPort;
import com.example.demo.domain.ports.user.UserInputPort;
import com.example.demo.domain.ports.user.UserOutputPort;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class UserInputPortDelegator implements UserInputPort, AddUserInputPort {

    private UserOutputPort userOutputPort;
    private AddUserOutputPort addUserOutputPort;


    @Override
    public List<User> getUsers() {
        return userOutputPort.getUsers();
    }

    @Override
    public User addUser(User user) {
      return addUserOutputPort.addUser(user);
    }
}


