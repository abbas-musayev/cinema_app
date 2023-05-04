package az.aist.cinema.application.service;

import az.aist.cinema.application.dto.account.AccountPaymentResponseDto;
import az.aist.cinema.application.dto.balance.BalanceChangeRequestDto;
import az.aist.cinema.application.dto.balance.BalanceResponseDto;

public interface BalanceService {

    BalanceResponseDto reduceBalance(BalanceChangeRequestDto request);
    BalanceResponseDto increaseBalance(BalanceChangeRequestDto request);
}
