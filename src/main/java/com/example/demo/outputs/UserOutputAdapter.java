package com.example.demo.outputs;

import com.example.demo.domain.models.User;
import com.example.demo.domain.ports.AddUserOutputPort;
import com.example.demo.domain.ports.UserOutputPort;
import com.example.demo.outputs.entities.UserEntity;
import com.example.demo.outputs.mappers.UserOutputMapper;
import com.example.demo.outputs.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserOutputAdapter implements UserOutputPort , AddUserOutputPort {

    private final UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        var users = userRepository.findAll();
        return UserOutputMapper.mapUserEntityListToUserList(users);
    }

    @Override
    public User addUser(User user) {
        UserEntity userEntity = UserOutputMapper.mapUserToUserEntity(user);
        UserEntity newUser = userRepository.save(userEntity);
        return UserOutputMapper.mapUserEntityToUser(newUser);
    }
}
