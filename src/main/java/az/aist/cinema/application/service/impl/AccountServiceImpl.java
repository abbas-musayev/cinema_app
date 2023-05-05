package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.auth.UserPrincipal;
import az.aist.cinema.application.dto.account.AccountRegisterRequestDto;
import az.aist.cinema.application.dto.balance.BalanceChangeRequestDto;
import az.aist.cinema.application.entity.AccountEnt;
import az.aist.cinema.application.entity.AuthorityEnt;
import az.aist.cinema.application.entity.BalanceEnt;
import az.aist.cinema.application.enums.Role;
import az.aist.cinema.application.mapper.AccountMapper;
import az.aist.cinema.application.repository.AccountRepository;
import az.aist.cinema.application.repository.BalanceRepository;
import az.aist.cinema.application.service.AccountService;
import az.aist.cinema.application.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService, UserDetailsService {

    private final AccountRepository accountRepository;
    private final BalanceService balanceService;
    private final AccountMapper accountMapper;

    @Override
    public void createAccount(AccountRegisterRequestDto request) {
        AccountEnt accountEnt = accountMapper.toEntity(request);
        accountEnt.setAuthorities(Set.of(AuthorityEnt.builder().role(Role.USER).build()));
        // call Balance service and save balance
        balanceService.increaseBalance(BalanceChangeRequestDto.builder()
                .accountUuid(accountEnt.getUuid())
                .amount(new BigDecimal(100))
                .build());
        accountRepository.save(accountEnt);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEnt accountEnt = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        return new UserPrincipal(accountEnt);
    }
}
