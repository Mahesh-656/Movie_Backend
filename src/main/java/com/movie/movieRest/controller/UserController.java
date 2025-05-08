package com.movie.movieRest.controller;

import com.movie.movieRest.DTO.UserProfileUpdatedRequest;
import com.movie.movieRest.model.User;
import com.movie.movieRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
    @RequestMapping("/api/v1/user")
    public class UserController {

        @Autowired
        private UserService userService;

        @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")

        public ResponseEntity<?> updateProfile(
                @RequestPart UserProfileUpdatedRequest request,
                @RequestPart(required = false) MultipartFile image,
                @AuthenticationPrincipal User user) throws IOException {

            User updatedUser = userService.updateUser(user.getEmail(), request, image);
            return ResponseEntity.ok(updatedUser);
        }

        @GetMapping("/me")
        @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")

        public ResponseEntity<?> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
            return ResponseEntity.ok(userService.getCurrentUser(userDetails.getUsername()));
        }


}
