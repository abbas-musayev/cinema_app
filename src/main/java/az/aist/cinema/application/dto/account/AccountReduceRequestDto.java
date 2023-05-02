package az.aist.cinema.application.dto.account;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountReduceRequestDto {
    private String accountUuid;
    private BigDecimal amount;
}
