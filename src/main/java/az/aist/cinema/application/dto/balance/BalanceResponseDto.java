package az.aist.cinema.application.dto.balance;

import az.aist.cinema.application.dto.CoreResponseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class BalanceResponseDto extends CoreResponseDto {
    private Long id;
}
