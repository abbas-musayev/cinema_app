package az.aist.cinema.application.dto.account;

import az.aist.cinema.application.enums.Valute;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountPaymentRequestDto {
    private String accountUuid;
    private Valute valute;
    private BigDecimal amount;
    private String transactionNumber;
}
