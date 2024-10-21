package com.example.demo.domain.ports.user;

import com.example.demo.domain.models.User;

public interface EditUserInputPort {
    User addUserToReality(long userId, long realityId);
}
