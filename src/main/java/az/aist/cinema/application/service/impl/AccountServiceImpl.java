package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.dto.account.AccountRequestDto;
import az.aist.cinema.application.entity.AccountEnt;
import az.aist.cinema.application.enums.Role;
import az.aist.cinema.application.mapper.AccountMapper;
import az.aist.cinema.application.repository.AccountRepo;
import az.aist.cinema.application.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;
    private final AccountMapper accountMapper;

    @Override
    public void createAccount(AccountRequestDto accountRequestDto) {
        AccountEnt accountEnt = accountMapper.toEntity(accountRequestDto);
        accountEnt.setBalance(BigDecimal.valueOf(0.0));
        accountEnt.setRole(Role.USER);
        accountRepo.save(accountEnt);
    }
}
