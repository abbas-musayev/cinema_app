package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.dto.SearchCriteria;
import az.aist.cinema.application.dto.hall.HallRequestDto;
import az.aist.cinema.application.dto.hall.HallResponseDto;
import az.aist.cinema.application.dto.movie.MovieResponseDto;
import az.aist.cinema.application.service.HallService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class HallServiceImpl implements HallService {
    @Override
    public void create(HallRequestDto request) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public MovieResponseDto edit(HallRequestDto request, Long id) {
        return null;
    }

    @Override
    public List<HallResponseDto> getAll() {
        return null;
    }

    @Override
    public List<HallResponseDto> search(List<SearchCriteria> request) {
        return null;
    }
}
