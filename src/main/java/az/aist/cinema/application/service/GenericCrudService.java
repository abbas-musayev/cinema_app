package az.aist.cinema.application.service;

import az.aist.cinema.application.dto.SearchCriteria;
import az.aist.cinema.application.dto.movie.MovieResponseDto;

import java.util.List;
import java.util.Set;

public interface GenericCrudService<R,E,T> {
    void create(R request);
    void delete(Long id);
    MovieResponseDto edit(R request, Long id);
    Set<T> getAll();
    Set<T> search(List<SearchCriteria> request);
}
