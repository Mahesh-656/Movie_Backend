package com.movie.movieRest.controller;


import com.movie.movieRest.model.Movie;
import com.movie.movieRest.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/movies")

public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/add")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addMovie(@RequestPart @Valid Movie movie, @RequestPart MultipartFile image) throws IOException {
        Movie savedMovie=movieService.addMovieWithImage(movie,image);
        return ResponseEntity.ok().body(savedMovie);
    }
    @GetMapping("/{movieId}")
    public ResponseEntity<?> getMovie(@PathVariable String movieId) {
        Movie fetch=movieService.getMovie(movieId);
        return ResponseEntity.ok(fetch);
    }
    @GetMapping
    public ResponseEntity<?> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PutMapping(value = "/update/{movieId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateMovie(@PathVariable String movieId,@RequestPart Movie movie, @RequestPart MultipartFile image) throws IOException {
        Movie updated=movieService.updateMovieWithImage(movieId,movie,image);
        return ResponseEntity.ok().body(updated);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable String id) throws IOException {
        movieService.delete(id);
        return ResponseEntity.ok("Movie Deleted Successfully");
    }
}
