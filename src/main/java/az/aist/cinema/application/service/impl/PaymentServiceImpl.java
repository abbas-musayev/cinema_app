package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.dto.balance.BalanceChangeRequestDto;
import az.aist.cinema.application.dto.balance.BalanceResponseDto;
import az.aist.cinema.application.dto.payment.PaymentAccountRequestDto;
import az.aist.cinema.application.dto.payment.PaymentFeignRequestDto;
import az.aist.cinema.application.dto.payment.PaymentResponseDto;
import az.aist.cinema.application.entity.AccountEnt;
import az.aist.cinema.application.entity.BalanceEnt;
import az.aist.cinema.application.entity.PaymentEnt;
import az.aist.cinema.application.entity.TicketEnt;
import az.aist.cinema.application.enums.Response;
import az.aist.cinema.application.enums.TicketStatus;
import az.aist.cinema.application.exception.CustomThrowException;
import az.aist.cinema.application.exception.ErrorCodesEnum;
import az.aist.cinema.application.exception.NotFoundCustomException;
import az.aist.cinema.application.repository.AccountRepository;
import az.aist.cinema.application.repository.BalanceRepository;
import az.aist.cinema.application.repository.PaymentRepository;
import az.aist.cinema.application.repository.TicketRepository;
import az.aist.cinema.application.service.BalanceService;
import az.aist.cinema.application.service.PaymentService;
import az.aist.cinema.application.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.OptimisticLock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BalanceRepository balanceRepo;
    private final AccountRepository accountRepo;
    private final BalanceService balanceService;
    private final TicketRepository ticketRepo;
    private final TicketService ticketService;

    @Transactional
    @OptimisticLock(excluded = false)
    @Override
    public PaymentResponseDto paymentForTicket(PaymentFeignRequestDto request) {

        List<String> ticketNumbers = request.getTicketNumber();
        String accountUuid = request.getAccountUuid();
        String transactionNumber = request.getTransactionNumber();

        // tekrar transaksiya oldugu yoxlanilir
        paymentRepository.getByTransactionNumber(transactionNumber)
                .orElseThrow(() -> new CustomThrowException(400, ErrorCodesEnum.DUBLICATE_TRANSACTION, "dublicate transaction"));

        // ve bu odenis melumati Payment cedveline yazilir
        // geriye succes qaytarilir

        // gelen account exists yoxlanilir
        AccountEnt account = accountRepo.findByUuid(accountUuid)
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.ACCOUNT_NOT_FOUND, "Account not found"));

        List<TicketEnt> tickets = ticketRepo.findByTicketNumber(ticketNumbers)
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.TICKET_NOT_FOUND, "Ticket not found"));

        // ehtimal ola bilerki gelen ticketlerden hansisa biri tapilmasin
        for (String ticket : ticketNumbers) {
            for (TicketEnt ticketEnt : tickets) {
                if (!ticketEnt.getTicketNumber().equals(ticket)) {
                    PaymentResponseDto response = new PaymentResponseDto();
                    response.setResponseCode("404");
                    response.setResponseText("Ticket not found, ticket number : " + ticket);
                    response.setTransactionNumber(request.getTransactionNumber());
                    return response;
                }
            }
        }
        // gelir 1 ve 2 ve 3
        // tapildi 1 ve 3
        // yoxlanis 1,3 icerinde 1,2,3

        BigDecimal sumOfTicketAmount = tickets.stream()
                .map(TicketEnt::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // eger mebleg ticketin mebleginnen az olarsa geri qaytarilir
        // beraber olarsa ticketler SOLD statusuna kecilir
        // cixildiqdan sonra artiq pul qalarsa balansa koculur

        // balansin ticket meblegine uygunlugu yoxlanilir
        if (sumOfTicketAmount.compareTo(request.getAmount()) >= 0) {
            // Ticketler SOLD statusuna kecdi
            List<TicketEnt> ticketEnts = tickets.stream()
                    .map(ticket -> {
                        ticket.setTicketStatus(TicketStatus.SOLD);
                        return ticket;
                    })
                    .collect(Collectors.toList());
            ticketRepo.saveAll(ticketEnts);

            BigDecimal subtract = sumOfTicketAmount.subtract(request.getAmount());
            if (subtract.compareTo(new BigDecimal(0)) > 0) {
                BalanceResponseDto response = balanceService.increaseBalance(BalanceChangeRequestDto.builder()
                        .accountUuid(accountUuid)
                        .amount(subtract)
                        .valute(request.getValute())
                        .build());

                if (response.getResponseCode().equals(Response.SUCCESS.name())) {
                    log.info("ACCOUNT BALANCE INCREASING");
                }
            }


            for (TicketEnt ticketEnt : ticketEnts) {
                PaymentEnt build = PaymentEnt.builder()
                        .ticket(ticketEnt)
                        .accountEnt(account)
                        .transactionNumber(request.getTransactionNumber())
                        .valute(request.getValute())
                        .amount(request.getAmount())
                        .build();
                paymentRepository.save(build);
            }

            PaymentResponseDto response = new PaymentResponseDto();
            response.setResponseCode("000");
            response.setResponseText("SUCCESS");
            response.setTransactionNumber(request.getTransactionNumber());
            return response;
        } else {
            PaymentResponseDto response = new PaymentResponseDto();
            response.setResponseCode("-101");
            response.setResponseText("insufficient amount");
            response.setTransactionNumber(request.getTransactionNumber());
            return response;
        }
    }

    @Transactional
    @Override
    public PaymentResponseDto ticketPaymentFromAccount(PaymentAccountRequestDto request) {

        String accountUuid = request.getAccounUuid();
        List<String> ticketNumbers = request.getTicketNumbers();

        checkTransaction(request.getTransactionNumber());

        AccountEnt account = accountRepo.findByUuid(accountUuid)
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.ACCOUNT_NOT_FOUND, "Account not found"));
        List<TicketEnt> tickets = ticketRepo.findByTicketNumber(ticketNumbers)
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.TICKET_NOT_FOUND, "Ticket not found"));
        BalanceEnt balance = balanceRepo.findByAccountUuid(account.getUuid())
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.BALANCE_NOT_FOUND, "balance not found"));

        BigDecimal sumOfTicketAmount = sumOfTicketAmount(tickets);

        // account balansi ticketlerin mebleginnen az olub olmamasi yoxlanilir
        if (balance.getBalance().compareTo(sumOfTicketAmount) < 0) {
            PaymentResponseDto response = new PaymentResponseDto();
            response.setResponseCode("-101");
            response.setResponseText("Insufficient amount in balance");
            response.setTransactionNumber(response.getTransactionNumber());
            return response;
        }
        // balansdan mebleg cixilir
        BalanceResponseDto balanceResponse = balanceService.reduceBalance(BalanceChangeRequestDto.builder()
                .accountUuid(accountUuid)
                .amount(sumOfTicketAmount)
                .transactionNumber(request.getTransactionNumber())
                .build());

        PaymentResponseDto response = new PaymentResponseDto();
        response.setResponseCode(balanceResponse.getResponseCode());
        response.setResponseText(balanceResponse.getResponseText());
        response.setTransactionNumber(request.getTransactionNumber());
        return response;

    }

    @Transactional
    @Override
    public PaymentResponseDto refundTicketPayment(PaymentAccountRequestDto request) {

        String accountUuid = request.getAccounUuid();
        List<String> ticketNumbers = request.getTicketNumbers();

        AccountEnt account = accountRepo.findByUuid(accountUuid)
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.ACCOUNT_NOT_FOUND, "Account not found"));
        List<TicketEnt> tickets = ticketRepo.findByTicketNumber(ticketNumbers)
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.TICKET_NOT_FOUND, "Ticket not found"));
        BalanceEnt balance = balanceRepo.findByAccountUuid(account.getUuid())
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.BALANCE_NOT_FOUND, "balance not found"));

        for (TicketEnt ticket : tickets) {
            if (ticketService.ticketCheckTicketDateIsExpired(ticket.getTicketNumber())) {
                PaymentResponseDto response = new PaymentResponseDto();
                response.setResponseCode("-105");
                response.setResponseText("Ticket date expired");
                response.setTransactionNumber(request.getTransactionNumber());
                return response;
            }
        }

        BigDecimal sumOfTicketAmount = sumOfTicketAmount(tickets);

        BalanceChangeRequestDto dto = BalanceChangeRequestDto.builder()
                .accountUuid(accountUuid)
                .amount(sumOfTicketAmount)
                .transactionNumber(request.getTransactionNumber())
                .build();

        BalanceResponseDto balanceResponse = balanceService.increaseBalance(dto);

        PaymentResponseDto response = new PaymentResponseDto();
        response.setResponseCode(balanceResponse.getResponseCode());
        response.setResponseText(balanceResponse.getResponseText());
        response.setTransactionNumber(request.getTransactionNumber());
        return response;
    }


    private void checkTransaction(String transactionNumber) {
        paymentRepository.getByTransactionNumber(transactionNumber)
                .orElseThrow(() -> new CustomThrowException(400, ErrorCodesEnum.DUBLICATE_TRANSACTION, "dublicate transaction"));
    }

    private BigDecimal sumOfTicketAmount(List<TicketEnt> tickets) {
        return tickets.stream()
                .map(TicketEnt::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
