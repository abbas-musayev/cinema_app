package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.dto.account.AccountPaymentResponseDto;
import az.aist.cinema.application.dto.balance.BalanceChangeRequestDto;
import az.aist.cinema.application.dto.balance.BalanceResponseDto;
import az.aist.cinema.application.entity.BalanceEnt;
import az.aist.cinema.application.exception.CustomThrowException;
import az.aist.cinema.application.exception.ErrorCodesEnum;
import az.aist.cinema.application.exception.NotFoundCustomException;
import az.aist.cinema.application.repository.BalanceRepository;
import az.aist.cinema.application.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;

    @Override
    public BalanceResponseDto increaseBalance(BalanceChangeRequestDto request) {
        BalanceEnt balance = balanceRepository.findByAccountUuid(request.getAccountUuid())
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.ACCOUNT_NOT_FOUND,"account not found"));
        balance.setBalance(balance.getBalance().add(request.getAmount()));
        balanceRepository.save(balance);
        return BalanceResponseDto.builder()
                .responseCode("000")
                .responseText("Balance reduced successfully")
                .build();
    }

    @Override
    public BalanceResponseDto reduceBalance(BalanceChangeRequestDto request) {
        BalanceEnt balance = balanceRepository.findByAccountUuid(request.getAccountUuid())
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.ACCOUNT_NOT_FOUND,"account not found"));
        BigDecimal subtract = balance.getBalance().subtract(request.getAmount());

        if (subtract.compareTo(new BigDecimal(0)) < 0){
            return BalanceResponseDto.builder()
                    .responseCode("-100")
                    .responseText("insufficient balance")
                    .transactionNumber(request.getTransactionNumber())
                    .build();
        }
        balance.setBalance(subtract);
        balanceRepository.save(balance);
        return BalanceResponseDto.builder()
                .responseCode("000")
                .responseText("SUCCESS REDUCE BALANCE")
                .transactionNumber(request.getTransactionNumber())
                .build();
    }
}
