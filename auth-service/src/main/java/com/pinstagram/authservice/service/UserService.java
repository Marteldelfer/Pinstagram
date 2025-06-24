package com.pinstagram.authservice.service;

import com.pinstagram.authservice.model.AuthUser;
import com.pinstagram.authservice.repository.AuthUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final AuthUserRepository authUserRepository;

    public UserService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    public Optional<AuthUser> findByEmail(String email) {
        return authUserRepository.findByEmail(email);
    }

}
