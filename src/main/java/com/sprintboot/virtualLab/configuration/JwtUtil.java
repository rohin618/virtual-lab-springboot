package com.sprintboot.virtualLab.configuration;


import com.sprintboot.virtualLab.entity.UserEntity;
import com.sprintboot.virtualLab.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtil {
    //secret key
    @Deprecated
    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    //expire time

    private final int jwtExpirationMs = 86400000;

    private UserRepository userRepository;


    public JwtUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //genarate token

    public String generateToken(String username) {
        Optional<UserEntity> user = userRepository.findByUserName(username);
        return Jwts.builder().setSubject(username)
                .claim("roles", Collections.emptyList())
                .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(secretKey).compact();
    }

    // extract the user name from token
    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    // token validation

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println(e);
            return false;
        }
    }


}
