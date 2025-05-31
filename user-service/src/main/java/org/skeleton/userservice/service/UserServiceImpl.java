package org.skeleton.userservice.service;

import lombok.RequiredArgsConstructor;
import org.skeleton.api.user.server.v1.model.RegisterRequest;
import org.skeleton.api.user.server.v1.model.User;
import org.skeleton.userservice.exception.UserAlreadyExistsException;
import org.skeleton.userservice.persistence.entity.Role;
import org.skeleton.userservice.persistence.entity.UserEntity;
import org.skeleton.userservice.persistence.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with given email already exists");
        }
        var user = new UserEntity();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User getUserById(Integer id) {
        return null;
    }
}
