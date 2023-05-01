package az.aist.cinema.application.mapper;

import az.aist.cinema.application.dto.movie.MovieRequestDto;
import az.aist.cinema.application.dto.movie.MovieResponseDto;
import az.aist.cinema.application.entity.MovieEnt;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieResponseDto toDto(MovieEnt movieEnt);

    MovieEnt toEntity(MovieRequestDto dto);

    List<MovieResponseDto> toListDto(List<MovieEnt> ents);

}
