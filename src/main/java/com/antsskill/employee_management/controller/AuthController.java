package com.antsskill.employee_management.controller;


import com.antsskill.employee_management.dto.JwtResponseDto;
import com.antsskill.employee_management.dto.RefreshTokenRequest;
import com.antsskill.employee_management.dto.UserDto;
import com.antsskill.employee_management.entity.RefreshToken;
import com.antsskill.employee_management.entity.User;
import com.antsskill.employee_management.jwt.JwtTokenProvider;
import com.antsskill.employee_management.repository.RefreshTokenRepository;
import com.antsskill.employee_management.repository.UserRepository;
import com.antsskill.employee_management.service_impl.RefreshTokenService;
import com.antsskill.employee_management.service_impl.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    public AuthController(UserService userService, RefreshTokenRepository refreshTokenRepository, JwtTokenProvider jwtTokenProvider, RefreshTokenService refreshTokenService, UserRepository userRepository) {
        this.userService = userService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User register(@RequestBody UserDto dto) {
        return userService.register(dto);
    }

    @PostMapping("/login")
    public JwtResponseDto login(@RequestBody UserDto dto) {
        return userService.login(dto);
    }

    @PostMapping("/refresh")
    public JwtResponseDto refresh(@RequestBody @Valid RefreshTokenRequest request) {

        RefreshToken oldToken =
                refreshTokenService.verifyAndRotate(request.getRefreshToken());

        User user = oldToken.getUser();

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
                );

        String newAccessToken =
                jwtTokenProvider.generateToken(authentication);

        RefreshToken newRefreshToken =
                refreshTokenService.createRefreshToken(user);

        return new JwtResponseDto(
                newAccessToken,
                newRefreshToken.getToken()
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(Authentication authentication) {

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow();

        refreshTokenService.deleteByUser(user);

        return ResponseEntity.ok("Logged out successfully");
    }

}
