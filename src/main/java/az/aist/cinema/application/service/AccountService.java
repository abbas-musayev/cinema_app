package az.aist.cinema.application.service;

import az.aist.cinema.application.dto.account.AccountPaymentRequestDto;
import az.aist.cinema.application.dto.account.AccountPaymentResponseDto;
import az.aist.cinema.application.dto.account.AccountRegisterRequestDto;

public interface AccountService {

    void createAccount(AccountRegisterRequestDto accountRequestDto);

//    AccountPaymentResponseDto increaseAccountBalance(AccountPaymentRequestDto reuqest);
//    AccountPaymentResponseDto reductionAccountBalance(AccountPaymentRequestDto reuqest);
}
