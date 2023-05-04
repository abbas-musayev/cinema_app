package az.aist.cinema.application.dto.payment;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentAccountRequestDto {
    private String accounUuid;
    private List<String> ticketNumbers;
    private String transactionNumber;
}
