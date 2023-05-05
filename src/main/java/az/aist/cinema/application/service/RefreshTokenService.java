package az.aist.cinema.application.service;

import az.aist.cinema.application.entity.RefreshTokenEnt;

import java.util.Optional;

public interface RefreshTokenService {

    RefreshTokenEnt createRefreshToken(String accountUuid);

    Optional<RefreshTokenEnt> findByToken(String refreshToken);

    RefreshTokenEnt verifyExpiration(RefreshTokenEnt token);
}
