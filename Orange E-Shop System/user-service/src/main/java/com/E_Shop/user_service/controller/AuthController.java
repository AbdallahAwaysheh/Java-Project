package com.E_Shop.user_service.controller;

import com.E_Shop.user_service.dto.AuthResponse;
import com.E_Shop.user_service.dto.LoginRequest;
import com.E_Shop.user_service.dto.RegisterRequest;
import com.E_Shop.user_service.model.User;
import com.E_Shop.user_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        User savedUser = authService.register(request);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
