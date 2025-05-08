package com.movie.movieRest.service;

import com.movie.movieRest.Otp.EmailOtpSender;
import com.movie.movieRest.DTO.LoginRequest;
import com.movie.movieRest.DTO.RegisterRequest;
import com.movie.movieRest.Repo.UserRepository;
import com.movie.movieRest.model.Role;
import com.movie.movieRest.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private EmailOtpSender emailOtpSender;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public void register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role role = userRepository.count() == 0 ? Role.ADMIN : Role.USER;
        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
//                .isOtpVerified(true)
                .role(role)
//                .otp(otp)
//                .expiryTime(LocalDateTime.now().plusMinutes(15))
                .build();

        userRepository.save(user);
//        emailOtpSender.sendOtp(registerRequest.getEmail(), otp);
    }

    public String login(LoginRequest loginRequest) {
        User user=userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
String token= jwtUtil.generateToken(loginRequest.getEmail());
return token;

    }


}
