package com.example.demo.inputs;

import com.example.demo.domain.exceptions.RealityNotFoundException;
import com.example.demo.domain.exceptions.UserNotFoundException;
import com.example.demo.domain.models.User;
import com.example.demo.domain.ports.user.CreateUserInputPort;
import com.example.demo.domain.ports.user.AssignRealityToUserInputPort;
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
    private final AssignRealityToUserInputPort assignRealityToUserInputPort;

    private final UserInputMapper userInputMapper;

    @GetMapping
    public List<UserResponse> getUsers() {
        List<User> users = userInputPort.getUsers();
        List<UserResponse> userResponses = userInputMapper.mapUserListToUserResponse(users);
        return userResponses;
    }

    @PostMapping()
    public UserResponse createUser(@RequestBody User user) {
        User newUser = createUserInputPort.createUser(user);
        return userInputMapper.mapUserToUserResponse(newUser);
    }

    // todo: UserResponse with the list of realities
    @PostMapping("/{userId}/reality/{realityId}")
    public UserResponse assignRealityToUser(@PathVariable Long userId, @PathVariable Long realityId) throws UserNotFoundException, RealityNotFoundException {
        User modifiedUser = assignRealityToUserInputPort.assign(userId, realityId);
        return userInputMapper.mapUserToUserResponse(modifiedUser);
    }
}
