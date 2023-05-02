package az.aist.cinema.application.dto.account;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountRegisterRequestDto {
    String username;
    Byte[] password;
}
