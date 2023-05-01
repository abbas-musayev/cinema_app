package az.aist.cinema.application.service;

import az.aist.cinema.application.dto.SearchCriteria;
import az.aist.cinema.application.dto.movie.MovieRequestDto;
import az.aist.cinema.application.dto.movie.MovieResponseDto;

import java.util.List;
import java.util.Set;

public interface MovieService {

    void saveMovie(MovieRequestDto request);
    List<MovieResponseDto> getAllMovie();
    List<MovieResponseDto> getMovie(List<SearchCriteria> request);
}
