package com.antsskill.employee_management.service_impl;

import com.antsskill.employee_management.entity.RefreshToken;
import com.antsskill.employee_management.entity.User;
import com.antsskill.employee_management.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private static final long EXPIRY_SECONDS = 7 * 24 * 60 * 60;

    private final RefreshTokenRepository repository;

    public RefreshTokenService(RefreshTokenRepository repository) {
        this.repository = repository;
    }

    public RefreshToken createRefreshToken(User user) {

        repository.findByUser(user).ifPresent(existingToken -> {
            existingToken.setRevoked(true);   // ✅ now works
            repository.save(existingToken);
        });

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(
                Instant.now().plusSeconds(EXPIRY_SECONDS)
        );
        refreshToken.setRevoked(false);

        return repository.save(refreshToken);
    }

    public RefreshToken verifyAndRotate(String token) {

        RefreshToken refreshToken = repository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (refreshToken.isRevoked()) {
            throw new RuntimeException("Refresh token reuse detected");
        }

        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            throw new RuntimeException("Refresh token expired");
        }

        refreshToken.setRevoked(true);
        repository.save(refreshToken);

        return refreshToken;
    }

    public void deleteByUser(User user) {
        repository.findByUser(user)
                .ifPresent(repository::delete);
    }
}



