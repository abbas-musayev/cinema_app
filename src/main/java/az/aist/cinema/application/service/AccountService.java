package az.aist.cinema.application.service;

import az.aist.cinema.application.dto.account.AccountPaymentRequestDto;
import az.aist.cinema.application.dto.account.AccountPaymentResponseDto;
import az.aist.cinema.application.dto.account.AccountReduceRequestDto;
import az.aist.cinema.application.dto.account.AccountRegisterRequestDto;
import az.aist.cinema.application.dto.payment.PaymentRequestDto;

public interface AccountService {

    void createAccount(AccountRegisterRequestDto accountRequestDto);

    AccountPaymentResponseDto increaseAccountBalance(AccountPaymentRequestDto reuqest);
}
