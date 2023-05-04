package az.aist.cinema.application.dto.balance;

import az.aist.cinema.application.enums.Valute;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BalanceChangeRequestDto {
    private String accountUuid;
    private Valute valute;
    private BigDecimal amount;
    private String transactionNumber;
}
