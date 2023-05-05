package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.dto.SearchCriteria;
import az.aist.cinema.application.dto.SearchSpecification;
import az.aist.cinema.application.dto.movie.MovieRequestDto;
import az.aist.cinema.application.dto.movie.MovieResponseDto;
import az.aist.cinema.application.entity.MovieEnt;
import az.aist.cinema.application.exception.ErrorCodesEnum;
import az.aist.cinema.application.exception.NotFoundCustomException;
import az.aist.cinema.application.mapper.MovieMapper;
import az.aist.cinema.application.repository.MovieRepository;
import az.aist.cinema.application.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public void create(MovieRequestDto request) {
        MovieEnt movieEnt = movieMapper.toEntity(request);
    }

    @Override
    public void delete(Long id) {
        MovieEnt movie = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.MOVIE_CURS_NOT_FOUND, "Movie not Found"));
        movieRepository.delete(movie);
    }

    @Override
    public MovieResponseDto edit(MovieRequestDto request,Long id) {
        MovieEnt movieEnt = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.MOVIE_CURS_NOT_FOUND, "Movie not Found"));

        movieMapper.partialUpdate(movieEnt,request);
        movieRepository.save(movieEnt);
        return movieMapper.toDto(movieEnt);
    }

    @Override
    public List<MovieResponseDto> getAll() {
        return movieMapper.toListDto(movieRepository.findAll());
    }

    @Override
    public List<MovieResponseDto> search(List<SearchCriteria> request) {
        List<Specification<MovieEnt>> specs = new ArrayList<>();
        for (SearchCriteria criteria : request) {
            specs.add(new SearchSpecification<>(criteria));
        }

        Specification<MovieEnt> spec = specs.stream().reduce(Specification::and).orElse(null);
        List<MovieEnt> all = movieRepository.findAll(spec);
        return movieMapper.toListDto(all);
    }
}
