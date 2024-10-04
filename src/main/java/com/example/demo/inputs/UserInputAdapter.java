package com.example.demo.inputs;

import com.example.demo.domain.models.User;
import com.example.demo.domain.ports.UserInputPort;
import com.example.demo.inputs.models.UserResponse;
import com.example.demo.outputs.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/users")
public class UserInputAdapter {
    private final UserInputPort userInputPort;

    @GetMapping
    public List<UserResponse> getUsers() {
        List<User> users = userInputPort.getUsers();
        List <UserResponse> userResponses = UserMapper.mapUserListToUserReponse(users);
        return userResponses;
    }
}
