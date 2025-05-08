package com.movie.movieRest.service;


import com.movie.movieRest.DTO.ReviewDTO;
import com.movie.movieRest.Repo.MovieRepository;
import com.movie.movieRest.Repo.ReviewRepository;
import com.movie.movieRest.model.Movie;
import com.movie.movieRest.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    //    add rating and movie average rating
    public Review addReview(String movieId, String userId, String userName, ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setMovieId(movieId);
        review.setUserId(userId);
        review.setUsername(userName);
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review = reviewRepository.save(review);

//        calculating the average
        List<Review> reviews = reviewRepository.findByMovieId(movieId);
        double avg = reviews.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0);


//        update Movie
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        movie.setAverageRating(avg);
        movieRepository.save(movie);

        return review;
    }

        public List<Review> getReviewsForMovie(String movieId){
          return reviewRepository.findByMovieId(movieId);
    }

    public double getAverageRating(String movieId) {
        return movieRepository.findById(movieId)
                .map(Movie::getAverageRating)
                .orElse(0.0);
    }
}
