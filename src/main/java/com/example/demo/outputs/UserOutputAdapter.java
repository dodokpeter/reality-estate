package com.example.demo.outputs;

import com.example.demo.domain.exceptions.RealityNotFoundException;
import com.example.demo.domain.exceptions.UserNotFoundException;
import com.example.demo.domain.models.User;
import com.example.demo.domain.ports.user.CreateUserOutputPort;
import com.example.demo.domain.ports.user.UserOutputPort;
import com.example.demo.outputs.entities.RealityEntity;
import com.example.demo.outputs.entities.UserEntity;
import com.example.demo.outputs.mappers.UserOutputMapper;
import com.example.demo.outputs.repositories.RealityRepository;
import com.example.demo.outputs.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class UserOutputAdapter implements UserOutputPort, CreateUserOutputPort {

    private final UserRepository userRepository;
    private final UserOutputMapper userOutputMapper;
    private final RealityRepository realityRepository;

    @Override
    public List<User> getUsers() {
        var users = userRepository.findAll();
        return userOutputMapper.mapUserEntityListToUserList(users);
    }

    @Override
    public User getUserById(Long userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isPresent()) {
            return userOutputMapper.mapUserEntityToUser(userEntityOptional.get());
        } else {
            log.error("User with the requested id was not found");
            throw new UserNotFoundException("Could not find user with this id.");
        }
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = userOutputMapper.mapUserToUserEntity(user);
        UserEntity newUser = userRepository.save(userEntity);
        return userOutputMapper.mapUserEntityToUser(newUser);
    }

    @Override
    public User getOwner(Long realityId) throws RealityNotFoundException {
        Optional<RealityEntity> realityOptional = realityRepository.findById(realityId);
        if (realityOptional.isPresent()) {
            return userOutputMapper.mapUserEntityToUser(realityOptional.get().getOwner());
        } else {
            log.error("Reality with the requested id was not found");
            throw new RealityNotFoundException("Could not find reality with this id.");
        }
    }
}
