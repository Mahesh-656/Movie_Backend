package com.movie.movieRest.controller;

import com.movie.movieRest.DTO.ReviewDTO;
import com.movie.movieRest.Repo.ReviewRepository;
import com.movie.movieRest.model.Review;
import com.movie.movieRest.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

//    Submitting the review
    @PostMapping("review/{movieId}")
    public ResponseEntity<Review> addReview(@PathVariable String movieId, @RequestBody ReviewDTO reviewDTO, @AuthenticationPrincipal UserDetails userDetails) {
         String username = userDetails.getUsername();
         String userId=userDetails.getUsername();
         Review saved=reviewService.addReview(movieId,userId,username,reviewDTO);
         return ResponseEntity.ok(saved);
    }

//    Get all reviews of an movie
    @GetMapping("/{movieId}/reviews")
    public ResponseEntity<List<Review>> getByMovie(@PathVariable String movieId) {
        return ResponseEntity.ok(reviewService.getReviewsForMovie(movieId));
    }

    @GetMapping("/{movieId}/average-rating")
    public ResponseEntity<Double> getAverage(@PathVariable String movieId) {
        return ResponseEntity.ok(reviewService.getAverageRating(movieId));
    }
}
