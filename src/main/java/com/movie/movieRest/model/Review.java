package com.movie.movieRest.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    private String id;
    @NotBlank
    private String movieId;
    @NotBlank
    private String userId;
    @NotBlank
    private String username;
    @Min(1)@Max(5)
    private int rating;
    @NotBlank
    private String comment;
    private LocalDateTime createdAt=LocalDateTime.now();
}
