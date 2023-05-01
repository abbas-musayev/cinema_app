package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.dto.session.SessionRequestDto;
import az.aist.cinema.application.dto.session.SessionResponseDto;
import az.aist.cinema.application.entity.SessionEnt;
import az.aist.cinema.application.mapper.SessionMapper;
import az.aist.cinema.application.repository.SessionRepo;
import az.aist.cinema.application.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionMapper sessionMapper;
    private final SessionRepo sessionRepo;

    @Override
    public void createSession(SessionRequestDto dto) {
        SessionEnt sessionEnt = sessionMapper.toEntity(dto);
        sessionRepo.save(sessionEnt);
    }

    @Override
    public List<SessionResponseDto> getAllSession() {
        return sessionMapper.toDtoList(sessionRepo.findAll());
    }

    @Override
    public List<SessionResponseDto> getSessionByMovie(String movieId) {
        return sessionMapper.toDtoList(sessionRepo.findByMovieId(movieId));
    }
}
