package az.aist.cinema.application.service;

import az.aist.cinema.application.dto.ticket.TicketRequestDto;
import az.aist.cinema.application.dto.ticket.TicketResponseDto;

public interface TicketService {

    TicketResponseDto createTicket(TicketRequestDto request);
}
