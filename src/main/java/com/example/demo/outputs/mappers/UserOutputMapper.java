package com.example.demo.outputs.mappers;


import com.example.demo.domain.models.User;
import com.example.demo.outputs.entities.UserEntity;

import java.util.List;

public class UserOutputMapper {
    public static User mapUserEntityToUser(UserEntity userEntity) {
        return new User(userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPhoneNumber()
        );
    }

    public static List<User> mapUserEntityListToUserList(List<UserEntity> users) {
        return users.stream().map(UserOutputMapper::mapUserEntityToUser)
                .toList();
    }

    public static UserEntity mapUserToUserEntity(User user) {
        return new UserEntity(user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber());
    }
}
