package com.jiaul.cloudapi.controller;

import com.jiaul.cloudapi.dto.AuthRequest;
import com.jiaul.cloudapi.dto.AuthResponse;
import com.jiaul.cloudapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> logIn(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.Login(authRequest));
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.signUp(authRequest));
    }
}
