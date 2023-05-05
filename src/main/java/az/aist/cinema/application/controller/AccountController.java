package az.aist.cinema.application.controller;

import az.aist.cinema.application.dto.account.AccountPaymentRequestDto;
import az.aist.cinema.application.dto.account.AccountPaymentResponseDto;
import az.aist.cinema.application.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

//    @PostMapping
//    public ResponseEntity<AccountPaymentResponseDto> increaseBalance(@RequestBody AccountPaymentRequestDto dto){
//        AccountPaymentResponseDto accountPaymentResponseDto = accountService.(dto);
//        return ResponseEntity.ok(accountPaymentResponseDto);
//    }

}
