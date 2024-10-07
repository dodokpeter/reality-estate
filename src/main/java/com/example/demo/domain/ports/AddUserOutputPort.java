package com.example.demo.domain.ports;

import com.example.demo.domain.models.User;

public interface AddUserOutputPort {
    void addUser(User user);
}
