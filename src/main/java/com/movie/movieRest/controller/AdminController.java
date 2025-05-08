package com.movie.movieRest.controller;

import com.movie.movieRest.model.User;
import com.movie.movieRest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')") // restricts all endpoints in this controller
public class AdminController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/promote/{userId}")
    public ResponseEntity<User> promoteToAdmin(@PathVariable String userId) {
        return ResponseEntity.ok(userService.promoteToAdmin(userId));
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}
