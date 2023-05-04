package az.aist.cinema.application.mapper;

import az.aist.cinema.application.dto.movie.MovieRequestDto;
import az.aist.cinema.application.dto.movie.MovieResponseDto;
import az.aist.cinema.application.entity.MovieEnt;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieResponseDto toDto(MovieEnt movieEnt);

    MovieEnt toEntity(MovieRequestDto dto);

    Set<MovieResponseDto> toListDto(List<MovieEnt> ents);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget MovieEnt entity, MovieRequestDto dto);

}
