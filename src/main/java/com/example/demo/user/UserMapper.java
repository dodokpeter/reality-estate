package com.example.demo.user;


import com.example.demo.domain.models.Reality;
import com.example.demo.domain.models.User;
import com.example.demo.outputs.adapters.entities.RealityEntity;
import com.example.demo.outputs.adapters.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    static User mapUserEntityListToUser(UserEntity userEntity) {
        return new User(userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPhoneNumber()
        );
    }

    static List<User> mapUserEntityListToUserList(List<UserEntity> users) {
        return users.stream().map(UserMapper::mapUserEntityListToUser)
                .toList();
    }
}
