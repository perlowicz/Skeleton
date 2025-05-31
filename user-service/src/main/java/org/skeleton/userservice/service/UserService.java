package org.skeleton.userservice.service;

import org.skeleton.api.user.server.v1.model.RegisterRequest;
import org.skeleton.api.user.server.v1.model.User;
import java.util.List;

public interface UserService {
    void registerUser(RegisterRequest registerRequest);
    List<User> getAllUsers();
    User getUserById(Integer id);
}
