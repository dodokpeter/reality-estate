package com.example.demo.domain.ports.user;

import com.example.demo.domain.models.Reality;
import com.example.demo.domain.models.User;

public interface AssignRealityToUserOutputPort {
    User assign(User user, Reality reality);
}

