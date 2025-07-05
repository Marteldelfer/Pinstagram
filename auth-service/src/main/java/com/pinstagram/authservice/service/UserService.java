package com.pinstagram.authservice.service;

import com.pinstagram.authservice.model.AuthUser;
import com.pinstagram.authservice.repository.AuthUserRepository;
import com.pinstagram.tokenservice.CreateAuthUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            AuthUserRepository authUserRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<AuthUser> findByEmail(String email) {
        return authUserRepository.findByEmail(email);
    }

    public void createUser(CreateAuthUserRequest request) {
        AuthUser authUser = AuthUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("user")
                .build();
        authUserRepository.save(authUser);
    }
}
