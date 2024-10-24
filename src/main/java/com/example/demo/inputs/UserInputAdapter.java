package com.example.demo.inputs;

import com.example.demo.domain.exceptions.RealityNotFoundException;
import com.example.demo.domain.exceptions.UserNotFoundException;
import com.example.demo.domain.models.User;
import com.example.demo.domain.ports.user.CreateUserInputPort;
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
    private final CreateUserInputPort createUserInputPort;

    private final UserInputMapper userInputMapper;

    @GetMapping
    public List<UserResponse> getUsers() {
        List<User> users = userInputPort.getUsers();
        List<UserResponse> userResponses = userInputMapper.mapUserListToUserResponse(users);
        return userResponses;
    }

    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return userInputMapper.mapUserToUserResponse(userInputPort.getUserById(userId));
    }

    @PostMapping()
    public UserResponse createUser(@RequestBody User user) {
        User newUser = createUserInputPort.createUser(user);
        return userInputMapper.mapUserToUserResponse(newUser);
    }

    @GetMapping("/owner/{realityId}")
    public UserResponse getOwnerOfReality(@PathVariable Long realityId) throws RealityNotFoundException {
        return userInputMapper.mapUserToUserResponse(userInputPort.getOwner(realityId));
    }
}
