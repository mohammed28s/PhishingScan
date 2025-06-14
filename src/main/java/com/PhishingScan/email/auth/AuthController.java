package com.PhishingScan.email.auth;


import com.PhishingScan.email.DTO.JwtResponse;
import com.PhishingScan.email.DTO.LoginRequest;
import com.PhishingScan.email.DTO.RegisterRequest;
import com.PhishingScan.email.entity.Users;
import com.PhishingScan.email.enums.Role;
import com.PhishingScan.email.repository.UserRepository;
import com.PhishingScan.email.security.JwtService;
import com.PhishingScan.email.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already in use");
        }

        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setRole(Role.USER);
        user.setCreated_at(LocalDateTime.now());  // this is for setting the current time

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        UserDetails userDetails = userRepository.findByEmail(request.getEmail())
                .map(u -> UserPrincipal.create(u))
                .orElseThrow();

        String token = jwtService.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
