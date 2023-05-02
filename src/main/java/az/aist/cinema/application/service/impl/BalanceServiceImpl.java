package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.dto.account.AccountPaymentResponseDto;
import az.aist.cinema.application.dto.account.AccountReduceRequestDto;
import az.aist.cinema.application.entity.BalanceEnt;
import az.aist.cinema.application.repository.BalanceRepository;
import az.aist.cinema.application.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;

    @Override
    public AccountPaymentResponseDto reduceBalance(AccountReduceRequestDto dto) {
        BalanceEnt balance = balanceRepository.findByAccountUuid(dto.getAccountUuid());
        balance.setBalance(balance.getBalance().add(dto.getAmount()));
        balanceRepository.save(balance);
        return AccountPaymentResponseDto.builder()
                .responseCode("000")
                .responseText("Balance reduced successfully")
                .build();
    }
}
