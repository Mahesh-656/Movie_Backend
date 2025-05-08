package com.movie.movieRest.controller;

import com.movie.movieRest.DTO.LoginRequest;
import com.movie.movieRest.Otp.OtpVerificationDTO;
import com.movie.movieRest.DTO.RegisterRequest;
import com.movie.movieRest.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return ResponseEntity.ok("User registered successfully Otp sent to mail");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        String token = authService.login(loginRequest);
        Cookie cookie=new Cookie("JWT",token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        cookie.setSecure(true);
        response.addCookie(cookie);

        Map<String,Object> map=new HashMap<>();
        map.put("message","Login Success");
        return ResponseEntity.ok(map);
//        return ResponseEntity.ok(Map.of("token", token));

    }

    }

