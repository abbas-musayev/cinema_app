package az.aist.cinema.application.service;

import az.aist.cinema.application.dto.account.AccountPaymentResponseDto;
import az.aist.cinema.application.dto.account.AccountReduceRequestDto;

public interface BalanceService {

    AccountPaymentResponseDto reduceBalance(AccountReduceRequestDto dto);
}
