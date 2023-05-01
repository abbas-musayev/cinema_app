package az.aist.cinema.application.service;

import az.aist.cinema.application.dto.session.SessionRequestDto;
import az.aist.cinema.application.dto.session.SessionResponseDto;

import java.util.List;

public interface SessionService {

    void createSession(SessionRequestDto dto);

    List<SessionResponseDto> getAllSession();

    List<SessionResponseDto> getSessionByMovie(String movieId);
}
