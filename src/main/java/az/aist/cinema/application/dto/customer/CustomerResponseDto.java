package az.aist.cinema.application.dto.customer;

import az.aist.cinema.application.dto.CoreResponseDto;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto extends CoreResponseDto {
    private Long id;
}
