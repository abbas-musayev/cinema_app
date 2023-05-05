package az.aist.cinema.application.mapper;

import az.aist.cinema.application.dto.ticket.TicketRequestDto;
import az.aist.cinema.application.dto.ticket.TicketResponseDto;
import az.aist.cinema.application.entity.TicketEnt;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketEnt toEntity(TicketRequestDto requestDto);

    TicketResponseDto toDto(TicketEnt ticketEnt);

    List<TicketResponseDto> toDtos(List<TicketEnt> ticketEnt);

}
