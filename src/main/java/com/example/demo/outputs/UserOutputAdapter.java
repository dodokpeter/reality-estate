package com.example.demo.outputs;

import com.example.demo.domain.models.User;
import com.example.demo.domain.ports.UserOutputPort;
import com.example.demo.user.UserMapper;
import com.example.demo.outputs.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserOutputAdapter implements UserOutputPort {

    private final UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        var users = userRepository.findAll();
        return UserMapper.mapUserEntityListToUserList(users);
    }

}
