package com.codecool.grannymanager.security;


import com.codecool.grannymanager.model.Role;
import com.codecool.grannymanager.model.User;
import com.codecool.grannymanager.repository.UserRepository;
import com.codecool.grannymanager.security.dtos.RegisterRequest;
import com.codecool.grannymanager.security.dtos.RegisterResponse;
import com.codecool.grannymanager.security.dtos.UserPaswordDTO;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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


        public Optional<RegisterResponse> register(RegisterRequest request) {
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

            RegisterResponse response = RegisterResponse.builder()
                    .message(request.getUserName() + "has been registered")
                    .build();
            return Optional.of(response);
        }

        public Cookie authenticate(UserPaswordDTO request) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUserName(),
                            request.getPassword()
                    )
            );

            User user = userRepository.findUserByUserName(request.getUserName()).orElseThrow(
                    ()->(new UsernameNotFoundException("user not found"))
            );
            String userName = user.getUserName();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());

            String jwtToken = jwtService.generateToken(userName, authority);
            Cookie cookie = cookieCreator(jwtToken);
            return cookie;
        }

        private Cookie cookieCreator(String jwtToken) {
            Cookie cookie = new Cookie("jwt", jwtToken);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(1800);
            return cookie;
        }


}
