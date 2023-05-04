package az.aist.cinema.application.service;

import az.aist.cinema.application.dto.movie.MovieRequestDto;
import az.aist.cinema.application.dto.movie.MovieResponseDto;
import az.aist.cinema.application.entity.MovieEnt;

public interface MovieService extends GenericCrudService<MovieRequestDto, MovieEnt,MovieResponseDto> {
}
