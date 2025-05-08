package com.movie.movieRest.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserProfileUpdatedRequest
{
    private String bio;
    private List<String> favouriteGenres;
}
