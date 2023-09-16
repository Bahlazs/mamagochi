package com.codecool.grannymanager.controller;


import com.codecool.grannymanager.security.AuthenticationService;
import com.codecool.grannymanager.security.dtos.RegisterResponse;
import com.codecool.grannymanager.security.dtos.UserPaswordDTO;
import com.codecool.grannymanager.security.dtos.AuthResponse;
import com.codecool.grannymanager.security.dtos.RegisterRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
        Optional<RegisterResponse> response = authService.register(registerRequest);
        if (response.isPresent()) {
            return ResponseEntity.ok(response.get());
        } else {

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Registration failed");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserPaswordDTO request, HttpServletResponse response) {
        Cookie cookie = authService.authenticate(request);

        response.addCookie(cookie);

        return ResponseEntity.ok("login success");
    }



}
