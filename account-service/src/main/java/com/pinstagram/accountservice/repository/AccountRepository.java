package com.pinstagram.accountservice.repository;

import com.pinstagram.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findByIdAndDeletedFalse(UUID id);

    List<Account> findAllByDeletedFalse();

    Optional<Account> findByEmailAndDeletedFalse(String email);

    boolean existsByUsername(String username);

}
