package com.example.demo.outputs.mappers;

import com.example.demo.domain.models.User;
import com.example.demo.outputs.entities.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserOutputMapper {
    User mapUserEntityToUser(UserEntity userEntity);
    List<User> mapUserEntityListToUserList(List<UserEntity> users);
    UserEntity mapUserToUserEntity(User user);
}
