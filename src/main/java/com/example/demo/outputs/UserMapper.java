package com.example.demo.outputs;


import com.example.demo.domain.models.User;
import com.example.demo.inputs.models.UserResponse;
import com.example.demo.outputs.entities.UserEntity;
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

    static UserResponse mapUserToUserResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber());


    }

    static List<UserResponse> mapUserListToUserReponse(List<User> users) {
        return users.stream().map(UserMapper::mapUserToUserResponse).toList();
    }

    static UserEntity mapUserToUserEntity(User user) {
        return new UserEntity(user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber());
    }
}
