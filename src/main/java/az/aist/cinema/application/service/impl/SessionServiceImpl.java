package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.dto.SearchCriteria;
import az.aist.cinema.application.dto.movie.MovieResponseDto;
import az.aist.cinema.application.dto.session.SessionRequestDto;
import az.aist.cinema.application.dto.session.SessionResponseDto;
import az.aist.cinema.application.entity.SessionEnt;
import az.aist.cinema.application.mapper.SessionMapper;
import az.aist.cinema.application.repository.SessionRepository;
import az.aist.cinema.application.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionMapper sessionMapper;
    private final SessionRepository sessionRepository;

    @Override
    public void create(SessionRequestDto dto) {
        SessionEnt sessionEnt = sessionMapper.toEntity(dto);
        sessionRepository.save(sessionEnt);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public SessionResponseDto edit(SessionRequestDto request, Long id) {
        return null;
    }

    @Override
    public List<SessionResponseDto> getAll() {
        return sessionMapper.toDtoList(sessionRepository.findAll());
    }

    @Override
    public List<SessionResponseDto> search(List<SearchCriteria> request) {
        return null;
    }
}
