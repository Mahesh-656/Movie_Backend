package com.movie.movieRest.controller;


import com.movie.movieRest.service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies/utility")
public class PaginationController {

    @Autowired
    private PaginationService paginationService;

    @GetMapping("/paged")
    public ResponseEntity<?> getPagedAndSortedMovies(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(defaultValue="releaseYear") String sortby,
                                                   @RequestParam(defaultValue = "desc")String order  ) {
        return ResponseEntity.ok(paginationService.getPagedAndSortedMovies(page, size,sortby,order));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchMovies(@RequestParam String title) {
        return ResponseEntity.ok(paginationService.searchMovies(title));
    }



}
