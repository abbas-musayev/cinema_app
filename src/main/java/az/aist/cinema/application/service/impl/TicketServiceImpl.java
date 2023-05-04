package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.dto.SearchCriteria;
import az.aist.cinema.application.dto.movie.MovieResponseDto;
import az.aist.cinema.application.dto.ticket.TicketRequestDto;
import az.aist.cinema.application.dto.ticket.TicketResponseDto;
import az.aist.cinema.application.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TicketServiceImpl implements TicketService {

    @Override
    public void create(TicketRequestDto request) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public MovieResponseDto edit(TicketRequestDto request, Long id) {
        return null;
    }

    @Override
    public Set<TicketResponseDto> getAll() {
        return null;
    }

    @Override
    public Set<TicketResponseDto> search(List<SearchCriteria> request) {
        return null;
    }
}
