package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.dto.balance.BalanceChangeRequestDto;
import az.aist.cinema.application.dto.balance.BalanceRequestDto;
import az.aist.cinema.application.dto.balance.BalanceResponseDto;
import az.aist.cinema.application.entity.AccountEnt;
import az.aist.cinema.application.entity.BalanceEnt;
import az.aist.cinema.application.exception.ErrorCodesEnum;
import az.aist.cinema.application.exception.NotFoundCustomException;
import az.aist.cinema.application.repository.AccountRepository;
import az.aist.cinema.application.repository.BalanceRepository;
import az.aist.cinema.application.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;
    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public BalanceResponseDto increaseBalance(BalanceChangeRequestDto request) {
        BalanceEnt balance = balanceRepository.findByAccountUuid(request.getAccountUuid())
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.ACCOUNT_NOT_FOUND,"account not found"));
        balance.setBalance(balance.getBalance().add(request.getAmount()));
        balanceRepository.save(balance);

        BalanceResponseDto response = new BalanceResponseDto();
        response.setResponseCode("000");
        response.setResponseText("SUCCESS INCREASE BALANCE");
        response.setTransactionNumber(request.getTransactionNumber());
        return response;
    }

    @Override
    public BalanceResponseDto createBalance(BalanceRequestDto request) {
        AccountEnt account = accountRepository.findByUuid(request.getAccountUuid())
                .orElseThrow(()-> new NotFoundCustomException(ErrorCodesEnum.ACCOUNT_NOT_FOUND,"Account Not Found"));

        BalanceEnt balance = new BalanceEnt();
        balance.setBalance(new BigDecimal(100));
        balance.setValute(request.getValute());
        balance.setAccount(account);
        BalanceEnt save = balanceRepository.save(balance);

        BalanceResponseDto response = new BalanceResponseDto();
        response.setResponseCode("000");
        response.setResponseText("SUCCESS CREATED BALANCE");
        response.setTransactionNumber(response.getTransactionNumber());
        response.setId(save.getId());
        return response;
    }

    @Transactional
    @Override
    public BalanceResponseDto reduceBalance(BalanceChangeRequestDto request) {
        BalanceEnt balance = balanceRepository.findByAccountUuid(request.getAccountUuid())
                .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.ACCOUNT_NOT_FOUND, "account not found"));
        BigDecimal subtract = balance.getBalance().subtract(request.getAmount());

        if (subtract.compareTo(new BigDecimal(0)) < 0) {
            BalanceResponseDto response = new BalanceResponseDto();
            response.setResponseCode("-101");
            response.setResponseText("insufficient balance");
            response.setTransactionNumber(request.getTransactionNumber());
            return response;
        }
        balance.setBalance(subtract);
        balanceRepository.save(balance);

        BalanceResponseDto response = new BalanceResponseDto();
        response.setResponseCode("000");
        response.setResponseText("SUCCESS REDUCE BALANCE");
        response.setTransactionNumber(request.getTransactionNumber());
        return response;
    }
}
