package az.aist.cinema.application.controller;

import az.aist.cinema.application.dto.payment.PaymentRequestDto;
import az.aist.cinema.application.dto.payment.PaymentResponseDto;
import az.aist.cinema.application.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDto> ticketPayment(@RequestBody PaymentRequestDto dto){
        PaymentResponseDto paymentResponseDto = paymentService.paymentForTicket(dto);
        return ResponseEntity.ok(paymentResponseDto);
    }

//    @PostMapping
//    public ResponseEntity<Void> ticketPaymentFromAccount(@RequestBody PaymentRequestDto dto){
//        paymentService.ticketPaymentFromAccount(dto);
//    }

}
