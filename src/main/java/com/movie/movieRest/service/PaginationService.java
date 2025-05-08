package com.movie.movieRest.service;

import com.movie.movieRest.DTO.PagedResponseDTO;
import com.movie.movieRest.Repo.MovieRepository;
import com.movie.movieRest.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaginationService {

    @Autowired
    private MovieRepository movieRepository;

    private PagedResponseDTO pagedResponseDTO;


    public List<Movie> searchMovies(String search) {
        return movieRepository.findByTitleContainingIgnoreCase(search);
    }

    public PagedResponseDTO<Movie> getPagedAndSortedMovies(int page, int size, String sortField, String sortDirection){
        Sort sort=sortDirection
                .equalsIgnoreCase("desc")
                ?Sort.by(sortField).descending()
                :Sort.by(sortField).ascending();
        Pageable pageable=PageRequest.of(page, size, sort);
        Page<Movie> moviePage=movieRepository.findAll(pageable);
        return new PagedResponseDTO(
                moviePage.getContent(),
                moviePage.getNumber(),
                moviePage.getTotalPages(),
                (int) moviePage.getTotalElements(),
                moviePage.getSize()
        );
    }
}
