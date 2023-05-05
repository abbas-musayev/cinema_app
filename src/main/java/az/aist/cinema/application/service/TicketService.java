package az.aist.cinema.application.service;

import az.aist.cinema.application.dto.ticket.TicketRequestDto;
import az.aist.cinema.application.dto.ticket.TicketResponseDto;
import az.aist.cinema.application.entity.TicketEnt;

public interface TicketService extends GenericCrudService<TicketRequestDto, TicketEnt,TicketResponseDto> {

    Boolean ticketCheckTicketDateIsExpired(String ticketNumber);

}
