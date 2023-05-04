package az.aist.cinema.application.auth;

import az.aist.cinema.application.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtCredentials {

    String iat;
    String username;
    String userId;
    Long exp;
    List<Role> role;



}
