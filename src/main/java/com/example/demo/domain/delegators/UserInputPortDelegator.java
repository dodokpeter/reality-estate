package com.example.demo.domain.delegators;

import com.example.demo.domain.models.User;
import com.example.demo.domain.ports.AddUserInputPort;
import com.example.demo.domain.ports.AddUserOutputPort;
import com.example.demo.domain.ports.UserInputPort;
import com.example.demo.domain.ports.UserOutputPort;
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


