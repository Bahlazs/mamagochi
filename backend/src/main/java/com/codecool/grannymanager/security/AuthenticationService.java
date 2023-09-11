package com.codecool.grannymanager.security;


import com.codecool.grannymanager.model.Role;
import com.codecool.grannymanager.model.User;
import com.codecool.grannymanager.repository.UserRepository;
import com.codecool.grannymanager.security.dtos.AuthRequest;
import com.codecool.grannymanager.security.dtos.AuthResponse;
import com.codecool.grannymanager.security.dtos.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public Optional<AuthResponse> register(RegisterRequest request) {
            Optional<User> optUser = userRepository.findUserByUserName(request.getUsername());
            if (optUser.isPresent()) {
                return Optional.empty();
            }
            var user = User.builder()
                    .userName(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .role(Role.USER)
                    .build();
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
            AuthResponse response = AuthResponse.builder()
                    .token(jwtToken)
                    .build();
            return Optional.of(response);
        }

        public AuthResponse authenticate(AuthRequest request) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            UserDetails user = userRepository.findUserByUserName(request.getUsername())
                    .orElseThrow(()-> new UsernameNotFoundException("username does not exist"));

            var jwtToken = jwtService.generateToken(user);

            return AuthResponse.builder()
                    .token(jwtToken)
                    .build();
        }
}
