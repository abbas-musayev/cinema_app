package az.aist.cinema.application.dto.account;

import az.aist.cinema.application.dto.CoreResponseDto;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDto extends CoreResponseDto {
    private Long id;
}
