package az.aist.cinema.application.dto.account;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountPaymentResponseDto {
    private String responseText;
    private String responseCode;
    private String transactionNumber;
}
