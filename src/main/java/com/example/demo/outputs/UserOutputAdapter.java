package com.example.demo.outputs;

import com.example.demo.domain.exceptions.RealityNotFoundException;
import com.example.demo.domain.exceptions.UserNotFoundException;
import com.example.demo.domain.models.User;
import com.example.demo.domain.ports.user.CreateUserOutputPort;
import com.example.demo.domain.ports.user.EditUserOutputPort;
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
public class UserOutputAdapter implements UserOutputPort, CreateUserOutputPort, EditUserOutputPort {

    private final UserRepository userRepository;
    private final UserOutputMapper userOutputMapper;
    private final RealityRepository realityRepository;

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
    public User assignRealityToUser(Long userId, Long realityId) throws UserNotFoundException, RealityNotFoundException {
        // check if the required user exists
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isPresent()) {
            log.info("User found, looking for the reality...");

            // check if the required reality exists
            Optional<RealityEntity> realityEntityOptional = realityRepository.findById(realityId);
            if (realityEntityOptional.isPresent()) {
                log.info("Reality found, assigning the user...");

                UserEntity userEntity = userEntityOptional.get();
                List<RealityEntity> realityEntityList = userEntity.getRealityEntities();

                RealityEntity realityEntity = realityEntityOptional.get();
                realityEntity.setUserEntity(userEntity);
                realityEntityList.add(realityEntity);
                realityRepository.save(realityEntity);

                userEntity.setRealityEntities(realityEntityList);
                UserEntity updatedUserEntity = userRepository.save(userEntity);
                return userOutputMapper.mapUserEntityToUser(updatedUserEntity);
            }
            else {
                log.error("Reality with this ID does not exist in the database");
                throw new RealityNotFoundException("Could not assign the reality to the user (reality not found)");
            }
        }
        else {
            log.error("User with this ID does not exist in the database");
            throw new UserNotFoundException("Could not assign the reality to the user (user not found)");
        }
    }
}
