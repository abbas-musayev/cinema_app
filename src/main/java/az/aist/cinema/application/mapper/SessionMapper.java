package az.aist.cinema.application.mapper;

import az.aist.cinema.application.dto.session.SessionRequestDto;
import az.aist.cinema.application.dto.session.SessionResponseDto;
import az.aist.cinema.application.entity.SessionEnt;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    SessionResponseDto toDto(SessionEnt ent);
    SessionEnt toEntity(SessionRequestDto dto);

    List<SessionResponseDto> toDtoList(List<SessionEnt> ents);
}
