package az.aist.cinema.application.dto.payment;

import az.aist.cinema.application.enums.Valute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentFromAccountRequestDto {
    private String accountUuid;
    private String transactionNumber;
    private List<String> ticketNumber;
}
