package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.auth.UserPrincipal;
import az.aist.cinema.application.dto.account.AccountRegisterRequestDto;
import az.aist.cinema.application.dto.account.AccountResponseDto;
import az.aist.cinema.application.dto.balance.BalanceChangeRequestDto;
import az.aist.cinema.application.dto.balance.BalanceRequestDto;
import az.aist.cinema.application.dto.balance.BalanceResponseDto;
import az.aist.cinema.application.entity.AccountEnt;
import az.aist.cinema.application.entity.AuthorityEnt;
import az.aist.cinema.application.entity.BalanceEnt;
import az.aist.cinema.application.enums.Role;
import az.aist.cinema.application.enums.Valute;
import az.aist.cinema.application.mapper.AccountMapper;
import az.aist.cinema.application.repository.AccountRepository;
import az.aist.cinema.application.repository.AuthorityRepository;
import az.aist.cinema.application.repository.BalanceRepository;
import az.aist.cinema.application.service.AccountService;
import az.aist.cinema.application.service.BalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService, UserDetailsService {

    private final AccountRepository accountRepository;
    private final BalanceService balanceService;
    private final AccountMapper accountMapper;
    private final AuthorityRepository authorityRepository;
    private final BalanceRepository balanceRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AccountResponseDto createAccount(AccountRegisterRequestDto request) {
        AccountEnt accountEnt = accountMapper.toEntity(request);

        String encode = passwordEncoder.encode(request.getPassword());

        AuthorityEnt build = AuthorityEnt.builder().role(Role.USER).build();
        authorityRepository.save(build);

        accountEnt.setAuthorities(Set.of(build));
        accountEnt.setPassword(encode);
        accountRepository.save(accountEnt);

        // call Balance service and save balance
        BalanceRequestDto balance = new BalanceRequestDto();
        balance.setAccountUuid(accountEnt.getUuid());
        balance.setValute(Valute.AZN);
        BalanceResponseDto balanceResponse = balanceService.createBalance(balance);

        accountEnt.setBalance(balanceRepository.getReferenceById(balanceResponse.getId()));
        accountRepository.save(accountEnt);
        AccountResponseDto response = new AccountResponseDto();
        response.setId(accountEnt.getId());
        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEnt accountEnt = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        return new UserPrincipal(accountEnt);
    }
}
