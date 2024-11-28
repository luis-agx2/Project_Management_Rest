package com.lag.projectmanagement.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    String generateToken(UserDetails userDetails);

    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    String getUsername(String token);

    boolean validateToken(String token, UserDetails userDetails);
}
