package org.skeleton.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.skeleton.api.user.server.v1.DefaultApi;
import org.skeleton.api.user.server.v1.model.AuthResponse;
import org.skeleton.api.user.server.v1.model.LoginRequest;
import org.skeleton.api.user.server.v1.model.RegisterRequest;
import org.skeleton.api.user.server.v1.model.User;
import org.skeleton.userservice.authentication.AuthenticationService;
import org.skeleton.userservice.service.UserService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements DefaultApi {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User getUserById(Integer id) {
        return null;
    }

    @Override
    public AuthResponse loginUser(LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }

    @Override
    public void registerUser(RegisterRequest registerRequest) {
        userService.registerUser(registerRequest);
    }
}
