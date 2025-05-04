package org.skeleton.userservice.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.skeleton.userservice.persistence.entity.UserEntity;
import org.skeleton.userservice.properties.JwtProperties;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtProperties jwtProperties;

    @Override
    public String generateToken(UserEntity user) {
        var secretBytes = Base64.getDecoder().decode(jwtProperties.getSecret());
        var key = new SecretKeySpec(secretBytes, SignatureAlgorithm.HS256.getJcaName());

        Date now = new Date(Instant.now().toEpochMilli());
        Date expiry = new Date(now.getTime() + jwtProperties.getExpirationMs());

        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
