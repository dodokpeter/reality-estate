package com.example.demo.domain.ports.user;

import com.example.demo.domain.exceptions.RealityNotFoundException;
import com.example.demo.domain.exceptions.UserNotFoundException;
import com.example.demo.domain.models.User;

public interface AssignRealityToUserOutputPort {
    User assign(Long userId, Long realityId) throws UserNotFoundException, RealityNotFoundException;
}

