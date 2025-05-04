package org.skeleton.userservice.authentication;

import org.skeleton.api.user.server.v1.model.AuthResponse;
import org.skeleton.api.user.server.v1.model.LoginRequest;

public interface AuthenticationService {
    AuthResponse login(LoginRequest loginRequest);
}
