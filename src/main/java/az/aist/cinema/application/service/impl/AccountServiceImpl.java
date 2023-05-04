package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.auth.UserPrincipal;
import az.aist.cinema.application.dto.account.AccountPaymentRequestDto;
import az.aist.cinema.application.dto.account.AccountPaymentResponseDto;
import az.aist.cinema.application.dto.account.AccountRegisterRequestDto;
import az.aist.cinema.application.entity.AccountEnt;
import az.aist.cinema.application.entity.Authority;
import az.aist.cinema.application.enums.Role;
import az.aist.cinema.application.exception.ErrorCodesEnum;
import az.aist.cinema.application.exception.NotFoundCustomException;
import az.aist.cinema.application.mapper.AccountMapper;
import az.aist.cinema.application.repository.AccountRepository;
import az.aist.cinema.application.repository.BalanceRepository;
import az.aist.cinema.application.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService, UserDetailsService {

    private final AccountRepository accountRepository;
    private final BalanceRepository balanceRepository;
    private final AccountMapper accountMapper;

    @Override
    public void createAccount(AccountRegisterRequestDto accountRequestDto) {
        AccountEnt accountEnt = accountMapper.toEntity(accountRequestDto);
//        accountEnt.setBalance();
        accountEnt.setAuthorities(Set.of(Authority.builder().role(Role.USER).build()));
        // call Balance service and save balance
        accountRepository.save(accountEnt);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEnt accountEnt = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        return new UserPrincipal(accountEnt);
    }
}
