package com.antsskill.employee_management.service_impl;

import com.antsskill.employee_management.dto.JwtResponseDto;
import com.antsskill.employee_management.dto.UserDto;
import com.antsskill.employee_management.entity.RefreshToken;
import com.antsskill.employee_management.entity.User;
import com.antsskill.employee_management.enums.Role;
import com.antsskill.employee_management.jwt.JwtTokenProvider;
import com.antsskill.employee_management.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtTokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;

    public UserService(UserRepository repository,
                       PasswordEncoder encoder,
                       AuthenticationManager authManager,
                       JwtTokenProvider tokenProvider, RefreshTokenService refreshTokenService) {
        this.repository = repository;
        this.encoder = encoder;
        this.authManager = authManager;
        this.tokenProvider = tokenProvider;
        this.refreshTokenService = refreshTokenService;
    }

    public User register(UserDto dto) {

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole(Role.USER); // default role

        return repository.save(user);
    }


    public JwtResponseDto login(UserDto dto) {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );

        String accessToken = tokenProvider.generateToken(authentication);

        User user = repository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(user); // ✅ FIX

        return new JwtResponseDto(
                accessToken,
                refreshToken.getToken()
        );
    }

}
