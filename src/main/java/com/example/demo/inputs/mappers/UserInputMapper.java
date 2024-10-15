package com.example.demo.inputs.mappers;

import com.example.demo.domain.models.User;
import com.example.demo.inputs.models.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserInputMapper {
    UserResponse mapUserToUserResponse(User user);
    List<UserResponse> mapUserListToUserResponse(List<User> users);
}
