package az.aist.cinema.application.dto.auth;

import lombok.Data;
import lombok.Getter;

@Data
public class RefreshTokenRequest {

    private String refreshToken;
}
