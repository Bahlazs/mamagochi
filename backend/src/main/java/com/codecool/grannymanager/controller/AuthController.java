package com.codecool.grannymanager.controller;


import com.codecool.grannymanager.model.User;
import com.codecool.grannymanager.security.AuthenticationService;
import com.codecool.grannymanager.security.dtos.AuthRequest;
import com.codecool.grannymanager.security.dtos.AuthResponse;
import com.codecool.grannymanager.security.dtos.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        Optional<AuthResponse> response = authService.register(registerRequest);
        if (response.isPresent()) {
            return ResponseEntity.ok(response.get());
        } else {

            return ResponseEntity.status(HttpStatus.CONFLICT).body("something went wrong");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }



}
