package com.example.demo.outputs;

import com.example.demo.domain.models.User;
import com.example.demo.domain.ports.user.CreateUserOutputPort;
import com.example.demo.domain.ports.user.EditUserOutputPort;
import com.example.demo.domain.ports.user.UserOutputPort;
import com.example.demo.outputs.entities.UserEntity;
import com.example.demo.outputs.mappers.UserOutputMapper;
import com.example.demo.outputs.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserOutputAdapter implements UserOutputPort, CreateUserOutputPort, EditUserOutputPort {

    private final UserRepository userRepository;
    private final UserOutputMapper userOutputMapper;

    @Override
    public List<User> getUsers() {
        var users = userRepository.findAll();
        return userOutputMapper.mapUserEntityListToUserList(users);
    }

    // todo: add get user by id

    @Override
    public User createUser(User user) {
        UserEntity userEntity = userOutputMapper.mapUserToUserEntity(user);
        UserEntity newUser = userRepository.save(userEntity);
        return userOutputMapper.mapUserEntityToUser(newUser);
    }

    @Override
    public User addUserToReality(long userId, long realityId) {
        // todo: get user by id
        User user = null;
        UserEntity userEntity = userOutputMapper.mapUserToUserEntity(user);

        // todo: add the reality to the user (add list of realities to user entity etc)

        UserEntity newUser = userRepository.save(userEntity);
        return userOutputMapper.mapUserEntityToUser(newUser);
    }
}
