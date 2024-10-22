package com.example.demo.outputs;

import com.example.demo.domain.exceptions.RealityNotFoundException;
import com.example.demo.domain.exceptions.UserNotFoundException;
import com.example.demo.domain.models.User;
import com.example.demo.domain.ports.user.CreateUserOutputPort;
import com.example.demo.domain.ports.user.AssignRealityToUserOutputPort;
import com.example.demo.domain.ports.user.UserOutputPort;
import com.example.demo.outputs.entities.RealityEntity;
import com.example.demo.outputs.entities.UserEntity;
import com.example.demo.outputs.mappers.UserOutputMapper;
import com.example.demo.outputs.repositories.RealityRepository;
import com.example.demo.outputs.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class UserOutputAdapter implements UserOutputPort, CreateUserOutputPort, AssignRealityToUserOutputPort {

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
            return userOutputMapper.mapUserEntityToUser(realityOptional.get().getUserEntity());
        } else {
            log.error("Reality with the requested id was not found");
            throw new RealityNotFoundException("Could not find reality with this id.");
        }
    }

    @Override
    public User assign(Long userId, Long realityId) throws UserNotFoundException, RealityNotFoundException {
        // todo: only  mappings / checks should remain here
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
