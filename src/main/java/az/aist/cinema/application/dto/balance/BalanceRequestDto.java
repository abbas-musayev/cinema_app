package az.aist.cinema.application.dto.balance;

import az.aist.cinema.application.enums.Valute;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceRequestDto {
    private String accountUuid;
    private Valute valute;
    private BigDecimal amount;
}
