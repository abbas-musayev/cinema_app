package az.aist.cinema.application.service;

import az.aist.cinema.application.dto.payment.PaymentRequestDto;
import az.aist.cinema.application.dto.payment.PaymentResponseDto;

public interface PaymentService {

    PaymentResponseDto paymentForTicket(PaymentRequestDto dto);

}
