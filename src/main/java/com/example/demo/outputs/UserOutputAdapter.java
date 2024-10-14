package com.example.demo.outputs;

import com.example.demo.domain.models.User;
import com.example.demo.domain.ports.user.AddUserOutputPort;
import com.example.demo.domain.ports.user.UserOutputPort;
import com.example.demo.outputs.entities.UserEntity;
import com.example.demo.outputs.mappers.OutputMapper;
import com.example.demo.outputs.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserOutputAdapter implements UserOutputPort , AddUserOutputPort {

    private final UserRepository userRepository;
    private final OutputMapper outputMapper;

    @Override
    public List<User> getUsers() {
        var users = userRepository.findAll();
        return outputMapper.mapUserEntityListToUserList(users);
    }

    @Override
    public User addUser(User user) {
        UserEntity userEntity = outputMapper.mapUserToUserEntity(user);
        UserEntity newUser = userRepository.save(userEntity);
        return outputMapper.mapUserEntityToUser(newUser);
    }
}
