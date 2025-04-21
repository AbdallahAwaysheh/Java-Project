package com.E_Shop.user_service.service;

import com.E_Shop.user_service.dto.AuthResponse;
import com.E_Shop.user_service.dto.LoginRequest;
import com.E_Shop.user_service.dto.RegisterRequest;
import com.E_Shop.user_service.model.User;
import com.E_Shop.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public User register (RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        return userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
        );
        var user = userRepository.findByUsername(request.getUserName()).orElseThrow();
        var jwt = jwtService.generateToken(user);

        return new AuthResponse(jwt);
    }
}
