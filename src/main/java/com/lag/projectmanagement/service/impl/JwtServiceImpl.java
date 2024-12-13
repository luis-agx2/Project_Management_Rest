package com.lag.projectmanagement.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import com.lag.projectmanagement.service.JwtService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
    private static final String SECRET_KEY = "AkB63SLoUIV/Mt3mh0T6VC++XZFzAYCdinNdCo3rRu0h5/NY1RS2BYjKmPcHSizV";
    private final SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA256");


    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        extraClaims.put("roles  ", roles);
        extraClaims.put("email", userDetails.getUsername());

        return generateToken(extraClaims, userDetails);
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (1000 * 60 * 24)))
                .signWith(secretKey)
                .compact();
    }

    @Override
    public String getUsername(String token) {
        return getClaims(token, Claims::getSubject);
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsername(token);

        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }

    private Date getExpirationDate(String token) {
        return getClaims(token, Claims::getExpiration);
    }

    private <T> T getClaims(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
