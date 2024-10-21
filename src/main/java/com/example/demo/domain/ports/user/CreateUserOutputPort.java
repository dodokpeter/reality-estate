package com.example.demo.domain.ports.user;

import com.example.demo.domain.models.User;

public interface CreateUserOutputPort {
    User createUser(User user);
}
