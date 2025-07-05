package com.pinstagram.authservice.service;

import com.pinstagram.authservice.dto.LoginRequestDto;
import com.pinstagram.authservice.grpc.AccountDetailsGrpcClient;
import com.pinstagram.authservice.util.JwtUtil;
import com.pinstagram.tokenservice.AccountDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AccountDetailsGrpcClient accountDetailsGrpcClient;
    private final JwtUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthService(
            UserService userService,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil,
            AccountDetailsGrpcClient accountDetailsGrpcClient
    ) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.accountDetailsGrpcClient = accountDetailsGrpcClient;
    }

    public Optional<String> authenticate(LoginRequestDto loginRequestDTO) {
        AccountDetails accountDetails = accountDetailsGrpcClient.getAccountDetails(loginRequestDTO.getEmail());
        return userService.findByEmail(loginRequestDTO.getEmail())
                .filter(u -> passwordEncoder.matches(loginRequestDTO.getPassword(), u.getPassword()))
                .map(u -> jwtUtil.generateToken(u, accountDetails));
    }

    public Claims getClaims(String token) {
        return jwtUtil.getClaims(token);
    }

    public boolean verifyAccount(String token) {
        logger.info("Verifying account with token {}", token);
        if (validToken(token)) {
            Claims claims = getClaims(token);
            logger.info("Verifying account with claims {}", claims);
            accountDetailsGrpcClient.verifyAccount(claims.getSubject());
            return true;
        } else  {
            return false;
        }

    }

    public boolean validToken(String token) {
        try {
            jwtUtil.validateToken(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
