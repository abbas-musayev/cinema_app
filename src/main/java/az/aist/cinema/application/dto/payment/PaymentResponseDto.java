package az.aist.cinema.application.dto.payment;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponseDto {
    private String responseText;
    private String responseCode;
    private String transactionNumber;
}
