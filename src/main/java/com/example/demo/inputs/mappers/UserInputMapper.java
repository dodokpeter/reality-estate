package com.example.demo.inputs.mappers;


import com.example.demo.domain.models.User;
import com.example.demo.inputs.models.UserResponse;

import java.util.List;


public class UserInputMapper {

    public static UserResponse mapUserToUserResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber());
    }

    public static List<UserResponse> mapUserListToUserResponse(List<User> users) {
        return users.stream().map(UserInputMapper::mapUserToUserResponse).toList();
    }
}
