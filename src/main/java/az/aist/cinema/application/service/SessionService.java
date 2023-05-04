package az.aist.cinema.application.service;

import az.aist.cinema.application.dto.session.SessionRequestDto;
import az.aist.cinema.application.dto.session.SessionResponseDto;
import az.aist.cinema.application.entity.SessionEnt;

import java.util.List;

public interface SessionService extends GenericCrudService<SessionRequestDto, SessionEnt,SessionResponseDto>{

}
