package az.aist.cinema.application.dto.auth;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private List<String> roles;
}
