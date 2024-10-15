package com.example.demo.inputs;

import com.example.demo.domain.models.User;
import com.example.demo.domain.ports.user.AddUserInputPort;
import com.example.demo.domain.ports.user.UserInputPort;
import com.example.demo.inputs.mappers.UserInputMapper;
import com.example.demo.inputs.models.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/users")
public class UserInputAdapter {
    private final UserInputPort userInputPort;
    private final AddUserInputPort addUserInputPort;

    private final UserInputMapper userInputMapper;

    @GetMapping
    public List<UserResponse> getUsers() {
        List<User> users = userInputPort.getUsers();
        List<UserResponse> userResponses = userInputMapper.mapUserListToUserResponse(users);
        return userResponses;
    }

    @PostMapping()
    public UserResponse addUser(@RequestBody User user) {
        User newUser = addUserInputPort.addUser(user);
        return userInputMapper.mapUserToUserResponse(newUser);
    }
}
