package com.movie.movieRest.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedResponseDTO<T>{

        private List<T> content;
        private int currentPage;
        private int totalPages;
        private long totalElements;
        private int pageSize;


}

