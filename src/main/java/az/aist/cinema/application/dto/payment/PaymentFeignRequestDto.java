package az.aist.cinema.application.dto.payment;

import az.aist.cinema.application.enums.Valute;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentFeignRequestDto {
    private String accountUuid;
    private Valute valute;
    private BigDecimal amount;
    private String transactionNumber;
    private List<String> ticketNumber;
}
