package az.aist.cinema.application.dto.balance;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BalanceResponseDto {
    private String responseText;
    private String responseCode;
    private String transactionNumber;
}
