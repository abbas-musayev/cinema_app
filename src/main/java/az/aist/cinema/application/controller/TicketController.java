package az.aist.cinema.application.controller;

import az.aist.cinema.application.dto.SearchCriteria;
import az.aist.cinema.application.dto.ticket.TicketRequestDto;
import az.aist.cinema.application.dto.ticket.TicketResponseDto;
import az.aist.cinema.application.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/ticket")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<Void> createTicket(@RequestBody TicketRequestDto request){
        ticketService.create(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDto>> searchTicket(@RequestBody List<SearchCriteria> searchCriteria){
        return ResponseEntity.ok(ticketService.search(searchCriteria));
    }
}
