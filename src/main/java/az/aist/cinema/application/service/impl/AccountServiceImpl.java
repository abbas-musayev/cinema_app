package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.dto.account.AccountPaymentRequestDto;
import az.aist.cinema.application.dto.account.AccountPaymentResponseDto;
import az.aist.cinema.application.dto.account.AccountReduceRequestDto;
import az.aist.cinema.application.dto.account.AccountRegisterRequestDto;
import az.aist.cinema.application.dto.payment.PaymentRequestDto;
import az.aist.cinema.application.entity.AccountEnt;
import az.aist.cinema.application.entity.BalanceEnt;
import az.aist.cinema.application.enums.Role;
import az.aist.cinema.application.mapper.AccountMapper;
import az.aist.cinema.application.repository.AccountRepository;
import az.aist.cinema.application.repository.BalanceRepository;
import az.aist.cinema.application.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final BalanceRepository balanceRepository;
    private final AccountMapper accountMapper;

    @Override
    public void createAccount(AccountRegisterRequestDto accountRequestDto) {
        AccountEnt accountEnt = accountMapper.toEntity(accountRequestDto);
//        accountEnt.setBalance();
        accountEnt.setRole(Role.USER);
        // call Balance service and save balance
        accountRepository.save(accountEnt);
    }

    @Override
    public AccountPaymentResponseDto increaseAccountBalance(AccountPaymentRequestDto reuqest) {
        return null;
    }
}
