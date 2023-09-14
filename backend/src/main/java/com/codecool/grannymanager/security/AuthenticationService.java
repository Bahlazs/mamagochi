package com.codecool.grannymanager.security;


import com.codecool.grannymanager.model.Role;
import com.codecool.grannymanager.model.User;
import com.codecool.grannymanager.repository.UserRepository;
import com.codecool.grannymanager.security.dtos.AuthResponse;
import com.codecool.grannymanager.security.dtos.RegisterRequest;
import com.codecool.grannymanager.security.dtos.UserPaswordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;


        public Optional<AuthResponse> register(RegisterRequest request) {
            Optional<User> optUser = userRepository.findUserByUserName(request.getUserName());
            if (optUser.isPresent()) {
                return Optional.empty();
            }
            var user = User.builder()
                    .userName(request.getUserName())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .role(Role.USER)
                    .build();
            userRepository.save(user);
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
            var jwtToken = jwtService.generateToken(user.getUserName(), authority);
            AuthResponse response = AuthResponse.builder()
                    .token(jwtToken)
                    .build();
            return Optional.of(response);
        }

        public AuthResponse authenticate(UserPaswordDTO request) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            User user = userRepository.findUserByUserName(request.getUsername()).orElseThrow(
                    ()->(new UsernameNotFoundException("user not found"))
            );
            String userName = user.getUserName();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());

            var jwtToken = jwtService.generateToken(userName, authority);

            return AuthResponse.builder()
                    .token(jwtToken)
                    .build();
        }


}
