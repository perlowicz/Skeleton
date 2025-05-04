package org.skeleton.userservice.authentication;

import lombok.RequiredArgsConstructor;
import org.skeleton.api.user.server.v1.model.AuthResponse;
import org.skeleton.api.user.server.v1.model.LoginRequest;
import org.skeleton.userservice.exception.UserNotAuthorizedException;
import org.skeleton.userservice.exception.UserNotFoundException;
import org.skeleton.userservice.jwt.JwtService;
import org.skeleton.userservice.persistence.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        var user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User with given email not found: " + loginRequest.getEmail()));

        if (!passwordEncoder.matches(user.getPassword(), loginRequest.getPassword())) {
            throw new UserNotAuthorizedException("Invalid password");
        }

        return new AuthResponse()
                .token(jwtService.generateToken(user));
    }
}
