package org.skeleton.userservice.jwt;

import org.skeleton.userservice.persistence.entity.UserEntity;

public interface JwtService {
    String generateToken(UserEntity user);
}
