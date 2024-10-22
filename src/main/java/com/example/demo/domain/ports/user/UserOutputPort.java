package com.example.demo.domain.ports.user;

import com.example.demo.domain.exceptions.RealityNotFoundException;
import com.example.demo.domain.models.User;

import java.util.List;
public interface UserOutputPort {
    List<User> getUsers();
    User getOwner(Long realityId) throws RealityNotFoundException;
}
