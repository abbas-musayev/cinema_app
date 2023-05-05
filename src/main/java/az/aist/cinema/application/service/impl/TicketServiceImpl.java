package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.dto.SearchCriteria;
import az.aist.cinema.application.dto.SearchSpecification;
import az.aist.cinema.application.dto.movie.MovieResponseDto;
import az.aist.cinema.application.dto.ticket.TicketRequestDto;
import az.aist.cinema.application.dto.ticket.TicketResponseDto;
import az.aist.cinema.application.entity.*;
import az.aist.cinema.application.enums.TicketStatus;
import az.aist.cinema.application.exception.ErrorCodesEnum;
import az.aist.cinema.application.exception.NotFoundCustomException;
import az.aist.cinema.application.mapper.TicketMapper;
import az.aist.cinema.application.repository.CustomerRepository;
import az.aist.cinema.application.repository.SessionRepository;
import az.aist.cinema.application.repository.TicketRepository;
import az.aist.cinema.application.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 6;

    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;
    private final SessionRepository sessionRepository;
    private final TicketMapper ticketMapper;

    @Override
    public void create(TicketRequestDto request) {
        CustomerEnt customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.ACCOUNT_NOT_FOUND, "Account not found"));

        SessionEnt session = sessionRepository.findById(request.getSessionId())
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.SESSION_NOT_FOUND, "session not found"));

        TicketEnt build = TicketEnt.builder()
                .ticketNumber(generate())
                .session(session)
                .customer(customer)
                .line(request.getLine())
                .place(request.getPlace())
                .sector(request.getSector())
                .ticketStatus(TicketStatus.BRON)
                .build();
        ticketRepository.save(build);
    }

    @Override
    public void delete(Long id) {
        TicketEnt ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.TICKET_NOT_FOUND, "ticket not found"));
        ticketRepository.delete(ticket);
    }

    @Override
    public MovieResponseDto edit(TicketRequestDto request, Long id) {
        return null;
    }

    @Override
    public List<TicketResponseDto> getAll() {
        List<TicketEnt> all = ticketRepository.findAll();
        return ticketMapper.toDtos(all);
    }

    @Override
    public List<TicketResponseDto> search(List<SearchCriteria> request) {
        List<Specification<TicketEnt>> specs = new ArrayList<>();
        for (SearchCriteria criteria : request) {
            specs.add(new SearchSpecification<>(criteria));
        }

        Specification<TicketEnt> spec = specs.stream().reduce(Specification::and).orElse(null);
        List<TicketEnt> all = ticketRepository.findAll(spec);
        return ticketMapper.toDtos(all);
    }

    private String generate() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        int randomNumber = 100000 + random.nextInt(900000);
        sb.append(randomNumber);
        return sb.toString();
    }

    @Transactional
    @Override
    public Boolean ticketCheckTicketDateIsExpired(String ticketNumber) {
        TicketEnt ticketEnt = ticketRepository.findByTicketNumber(ticketNumber)
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.TICKET_NOT_FOUND, "ticket not found"));

        SessionEnt session = ticketEnt.getSession();
        LocalDate endDate =session.getEndDate();
        String[] split = session.getEnd_hour().split(":");
        LocalTime time = LocalTime.of(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime ticketEntDate = LocalDateTime.of(endDate, time);
        return now.isBefore(ticketEntDate);
    }
}
