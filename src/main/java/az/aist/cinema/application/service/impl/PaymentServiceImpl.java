package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.dto.account.AccountPaymentRequestDto;
import az.aist.cinema.application.dto.account.AccountPaymentResponseDto;
import az.aist.cinema.application.dto.account.AccountReduceRequestDto;
import az.aist.cinema.application.dto.payment.PaymentRequestDto;
import az.aist.cinema.application.dto.payment.PaymentResponseDto;
import az.aist.cinema.application.entity.AccountEnt;
import az.aist.cinema.application.entity.BalanceEnt;
import az.aist.cinema.application.entity.TicketEnt;
import az.aist.cinema.application.enums.Response;
import az.aist.cinema.application.enums.TicketStatus;
import az.aist.cinema.application.repository.AccountRepository;
import az.aist.cinema.application.repository.BalanceRepository;
import az.aist.cinema.application.repository.PaymentRepository;
import az.aist.cinema.application.repository.TicketRepository;
import az.aist.cinema.application.service.AccountService;
import az.aist.cinema.application.service.BalanceService;
import az.aist.cinema.application.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.OptimisticLock;
import org.hibernate.annotations.OptimisticLocking;
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
    private final AccountService accountService;
    private final BalanceService balanceService;
    private final TicketRepository ticketRepo;

    @Transactional
    @OptimisticLock(excluded = false)
    @Override
    public PaymentResponseDto paymentForTicket(PaymentRequestDto request) {
        List<String> ticketNumbers = request.getTicketNumber();
        String accountUuid = request.getAccountUuid();

        List<TicketEnt> tickets = ticketRepo.findByTicketNumber(ticketNumbers);

        AccountEnt account = accountRepo.findByUuid(accountUuid);

        BalanceEnt balance = balanceRepo.findByAccountUuid(account.getUuid());

        // balans yoxlanisi
        if (balance.getBalance().compareTo(request.getAmount()) > 0){
            return PaymentResponseDto.builder()
                    .responseCode("100")
                    .responseText("Insufficient amount in balance")
                    .transactionNumber(request.getTransactionNumber())
                    .build();
        }

        BigDecimal sumOfTicketAmount = tickets.stream()
                .map(TicketEnt::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        // eger gelen mebleg ticketin mebleginen az olarsa xeta qaytaririq
        if (sumOfTicketAmount.equals(request.getAmount())){
            AccountReduceRequestDto requestDto = new AccountReduceRequestDto();
            requestDto.setAccountUuid(accountUuid);
            requestDto.setAmount(request.getAmount());
            //  Balans azaldılması
            AccountPaymentResponseDto response = balanceService.reduceBalance(requestDto);
            if (response.getResponseCode().equals(Response.SUCCESS.name())){
                // Ticketler SOLD statusuna kecdi
                List<TicketEnt> ticketEnts = tickets.stream()
                        .map(ticket -> {
                            ticket.setTicketStatus(TicketStatus.SOLD);
                            return ticket;
                        })
                        .collect(Collectors.toList());
                ticketRepo.saveAll(ticketEnts);

                return PaymentResponseDto.builder()
                        .responseCode("000")
                        .responseText("SUCCESS")
                        .transactionNumber(request.getTransactionNumber())
                        .build();
            }else {
                return null;
            }

        }else if (sumOfTicketAmount.subtract(request.getAmount()).compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal subtract = sumOfTicketAmount.subtract(request.getAmount());
            AccountPaymentResponseDto response = accountService.increaseAccountBalance(AccountPaymentRequestDto.builder()
                    .accountUuid(accountUuid)
                    .amount(subtract)
                    .valute(request.getValute())
                    .transactionNumber(request.getTransactionNumber())
                    .build());
            if (response.getResponseCode().equals(Response.SUCCESS.name())){
                log.info("ACCOUNT BALANCE INCREASING");
            }
            return PaymentResponseDto.builder()
                    .responseCode("000")
                    .responseText("SUCCESS")
                    .transactionNumber(request.getTransactionNumber())
                    .build();
        } else {
            return PaymentResponseDto.builder()
                    .responseCode("400")
                    .responseText("Ödəniş məbləği ticket məbləği ilə düzgün deyil")
                    .transactionNumber(request.getTransactionNumber())
                    .build();
        }
    }
}
