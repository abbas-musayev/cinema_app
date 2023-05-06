package az.aist.cinema.application.service;

import az.aist.cinema.application.dto.account.AccountRegisterRequestDto;
import az.aist.cinema.application.dto.account.AccountResponseDto;

public interface AccountService {

    AccountResponseDto createAccount(AccountRegisterRequestDto accountRequestDto);

//    AccountPaymentResponseDto increaseAccountBalance(AccountPaymentRequestDto reuqest);
//    AccountPaymentResponseDto reductionAccountBalance(AccountPaymentRequestDto reuqest);
}
