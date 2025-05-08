package com.movie.movieRest.service;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.movie.movieRest.Repo.MovieRepository;
import com.movie.movieRest.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service

public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private Cloudinary cloudinary;

    public Movie addMovieWithImage( Movie movie, MultipartFile image) throws IOException {
        boolean exists = movieRepository.existsByTitleIgnoreCaseAndDirectorIgnoreCaseAndReleaseYear(
                movie.getTitle(), movie.getDirector(), movie.getReleaseYear());

        if (exists) {
            throw new RuntimeException("Movie with the same title, director, and release year already exists.");
        }

        Map upload=cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
        if(upload.get("secure_url")==null){
            throw new RuntimeException("Secure URL is not available");
        }
        String url=upload.get("secure_url").toString();
        String publicId=upload.get("public_id").toString();

        movie.setPosterUrl(url);
        movie.setPosterPublicId(publicId);

        return movieRepository.save(movie);
    }

    public Movie updateMovieWithImage(String id, Movie movie, MultipartFile image) throws IOException {
        Movie existingMovie=movieRepository.findById(id).orElseThrow(()->new RuntimeException("Movie not found"));
        if(existingMovie.getPosterPublicId()!=null){
            deletePoster(existingMovie.getPosterPublicId());
        }
        Map upload=cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
        if(upload.get("secure_url")==null){
            throw new RuntimeException("Secure URL is not available");
        }
        String url=upload.get("secure_url").toString();
        String publicId=upload.get("public_id").toString();
      existingMovie.setPosterUrl(url);
      existingMovie.setPosterPublicId(publicId);
      existingMovie.setMovieCast(movie.getMovieCast());
      existingMovie.setDirector(movie.getDirector());
      existingMovie.setStudio(movie.getStudio());
      existingMovie.setTitle(movie.getTitle());
      existingMovie.setReleaseYear(movie.getReleaseYear());
        return movieRepository.save(existingMovie);
    }

    public void deletePoster(String publicId) throws IOException {
        cloudinary.uploader().destroy(publicId,ObjectUtils.emptyMap());
    }
    public void delete(String id){
        Movie movie=movieRepository.findById(id).orElseThrow(()->new RuntimeException("Movie not found"));
        try {
            deletePoster(movie.getPosterPublicId());
            movieRepository.delete(movie);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Movie getMovie(String movieId) {
       return movieRepository.findById(movieId).orElseThrow(()->new RuntimeException("Movie not found"));

    }

    public List<Movie> getAllMovies() {

        return movieRepository.findAll();

    }
}
