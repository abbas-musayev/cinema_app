package az.aist.cinema.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoreResponseDto {
    protected String responseText;
    protected String responseCode;
    protected String transactionNumber;
}
