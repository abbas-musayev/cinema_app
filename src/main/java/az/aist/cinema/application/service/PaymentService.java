package az.aist.cinema.application.service;

import az.aist.cinema.application.dto.payment.PaymentAccountRequestDto;
import az.aist.cinema.application.dto.payment.PaymentFeignRequestDto;
import az.aist.cinema.application.dto.payment.PaymentResponseDto;

public interface PaymentService {

    PaymentResponseDto paymentForTicket(PaymentFeignRequestDto dto);

    PaymentResponseDto ticketPaymentFromAccount(PaymentAccountRequestDto dto);

    PaymentResponseDto refundTicketPayment(PaymentAccountRequestDto dto);
}
