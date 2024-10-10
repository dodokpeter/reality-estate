package com.example.demo.domain.ports.user;

import com.example.demo.domain.models.User;

public interface AddUserInputPort {
    User addUser(User user);
}
