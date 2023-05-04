package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.dto.SearchCriteria;
import az.aist.cinema.application.dto.SearchSpecification;
import az.aist.cinema.application.dto.movie.MovieRequestDto;
import az.aist.cinema.application.dto.movie.MovieResponseDto;
import az.aist.cinema.application.entity.MovieEnt;
import az.aist.cinema.application.exception.ErrorCodesEnum;
import az.aist.cinema.application.exception.NotFoundCustomException;
import az.aist.cinema.application.mapper.MovieMapper;
import az.aist.cinema.application.repository.MovieRepo;
import az.aist.cinema.application.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepo movieRepo;
    private final MovieMapper movieMapper;

    @Override
    public void create(MovieRequestDto request) {
        MovieEnt movieEnt = movieMapper.toEntity(request);
    }

    @Override
    public void delete(Long id) {
        MovieEnt movie = movieRepo.findById(id)
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.MOVIE_CURS_NOT_FOUND, "Movie not Found"));
        movieRepo.delete(movie);
    }

    @Override
    public MovieResponseDto edit(MovieRequestDto request,Long id) {
        MovieEnt movieEnt = movieRepo.findById(id)
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.MOVIE_CURS_NOT_FOUND, "Movie not Found"));

        movieMapper.partialUpdate(movieEnt,request);
        movieRepo.save(movieEnt);
        return movieMapper.toDto(movieEnt);
    }

    @Override
    public Set<MovieResponseDto> getAll() {
        return movieMapper.toListDto(movieRepo.findAll());
    }

    @Override
    public Set<MovieResponseDto> search(List<SearchCriteria> request) {
        List<Specification<MovieEnt>> specs = new ArrayList<>();
        for (SearchCriteria criteria : request) {
            specs.add(new SearchSpecification<>(criteria));
        }

        Specification<MovieEnt> spec = specs.stream().reduce(Specification::and).orElse(null);
        List<MovieEnt> all = movieRepo.findAll(spec);
        return movieMapper.toListDto(all);
    }
}
