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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;
        private final UserDetailsService userDetailsService;

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

            User user = userRepository.findUserByUserName(request.getUsername()).orElseThrow(
                    ()->(new UsernameNotFoundException("user not found"))
            );

            var jwtToken = jwtService.generateToken(user);

            return AuthResponse.builder()
                    .token(jwtToken)
                    .build();
        }

        public org.springframework.security.core.userdetails.User getSecUser (String userName,
                                                                              UserRepository userRepository) {
            User dataUser = userRepository.findUserByUserName(userName).orElseThrow(
                    () -> (new UsernameNotFoundException("use not found"))
            );
            org.springframework.security.core.userdetails.User user =
                    new org.springframework.security.core.userdetails.User(dataUser.getUserName(), dataUser.getPassword(),
                            List.of(new SimpleGrantedAuthority(dataUser.getRole().name())));
            return user;
        }
}
