package com.pinstagram.authservice.util;

import com.pinstagram.authservice.model.AuthUser;
import com.pinstagram.tokenservice.TokenRequest;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key secretKey;

    public JwtUtil(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    /*public String generateToken(AuthUser authUser) { // TODO get fields from account-service
        return Jwts.builder()
                .subject(authUser.getEmail())
                .claim("id", authUser.getId())
                .claim("name", authUser.())
                .claim("username", authUser.getUsername())
                .claim("role", "user")
                .claim("validated", true)
                .issuedAt(new Date())
                .expiration(DateUtils.addDays(new Date(), 3))
                .signWith(secretKey)
                .compact();
    }*/

    public String generateUnvalidatedToken(TokenRequest request) {
        return Jwts.builder()
                .subject(request.getEmail())
                .claim("id", request.getId())
                .claim("name", request.getName())
                .claim("username", request.getUsername())
                .claim("role", "user")
                .claim("validated", false)
                .issuedAt(new Date())
                .expiration(DateUtils.addMinutes(new Date(), 15))
                .signWith(secretKey)
                .compact();
    }

    public void validateToken(String token) {
        try {
            Jwts.parser().verifyWith((SecretKey) secretKey)
                    .build().parseSignedClaims(token);
        } catch (JwtException e) {
            throw new JwtException("Invalid JWT token");
        }
    }
}
