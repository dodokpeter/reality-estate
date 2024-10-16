package com.example.demo.domain.ports.user;

import com.example.demo.domain.models.User;

import java.util.List;

public interface UserInputPort {
    List<User> getUsers();

}
