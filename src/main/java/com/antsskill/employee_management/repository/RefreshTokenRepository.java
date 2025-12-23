package com.antsskill.employee_management.repository;

import com.antsskill.employee_management.entity.RefreshToken;
import com.antsskill.employee_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository
        extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByUser(User user);

    Optional<RefreshToken> findByToken(String token);
}