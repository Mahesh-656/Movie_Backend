package com.movie.movieRest.Repo;

import com.movie.movieRest.model.User;
import com.movie.movieRest.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends MongoRepository<Movie, String> {
Optional<Movie> findById(String id);
    // MovieRepository.java
    boolean existsByTitleIgnoreCaseAndDirectorIgnoreCaseAndReleaseYear(String title, String director, Integer releaseYear);
    List<Movie> findByTitleContainingIgnoreCase(String title);
    @Override
    Page<Movie> findAll(Pageable pageable);
}
