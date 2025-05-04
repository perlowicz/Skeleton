package org.skeleton.userservice.service;

import org.skeleton.api.user.server.v1.model.RegisterRequest;


public interface UserService {
    void registerUser(RegisterRequest registerRequest);
}
