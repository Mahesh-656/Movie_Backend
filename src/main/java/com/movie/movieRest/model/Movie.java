package com.movie.movieRest.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "movie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    private String id;

    @NotBlank(message = "Please provide a movie title")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @NotBlank(message = "Please provide the director's name")
    @Size(min = 2, max = 100, message = "Director's name must be between 2 and 100 characters")
    private String director;

    @NotBlank(message = "Please provide the movie's studio")
    @Size(min = 2, max = 100, message = "Studio name must be between 2 and 100 characters")
    private String studio;

    @NotNull(message = "Movie cast cannot be null")
    @Size(min = 1, message = "At least one cast member is required")
    private Set<@NotBlank(message = "Cast member name cannot be blank") String> movieCast;

    @NotNull(message = "Please provide a release year")
    @Min(value = 1888, message = "Movies did not exist before 1888")
    @Max(value = 2100, message = "Please provide a valid future year")
    private Integer releaseYear;

    private String posterUrl; // No @NotBlank here
    private String posterPublicId;
    private Double averageRating = 0.0;
    private String downloadLink;
}
