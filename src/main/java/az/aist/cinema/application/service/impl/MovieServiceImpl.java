package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.dto.SearchCriteria;
import az.aist.cinema.application.dto.SearchSpecification;
import az.aist.cinema.application.dto.movie.MovieRequestDto;
import az.aist.cinema.application.dto.movie.MovieResponseDto;
import az.aist.cinema.application.entity.MovieEnt;
import az.aist.cinema.application.mapper.MovieMapper;
import az.aist.cinema.application.repository.MovieRepo;
import az.aist.cinema.application.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepo movieRepo;
    private final MovieMapper movieMapper;

    @Override
    public void saveMovie(MovieRequestDto request) {
        MovieEnt movieEnt = movieMapper.toEntity(request);
    }

    @Override
    public List<MovieResponseDto> getAllMovie() {
        return movieMapper.toListDto(movieRepo.findAll());
    }

    @Override
    public List<MovieResponseDto> getMovie(List<SearchCriteria> request) {
        List<Specification<MovieEnt>> specs = new ArrayList<>();
        for (SearchCriteria criteria : request) {
            specs.add(new SearchSpecification<>(criteria));
        }

        Specification<MovieEnt> spec = specs.stream().reduce(Specification::and).orElse(null);
        List<MovieEnt> all = movieRepo.findAll(spec);
        return movieMapper.toListDto(all);
    }
}
