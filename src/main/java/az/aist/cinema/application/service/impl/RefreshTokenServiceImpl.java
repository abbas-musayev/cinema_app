package az.aist.cinema.application.service.impl;

import az.aist.cinema.application.dto.auth.RefreshTokenRequest;
import az.aist.cinema.application.entity.RefreshTokenEnt;
import az.aist.cinema.application.exception.AuthRequestExcetion;
import az.aist.cinema.application.exception.ErrorCodesEnum;
import az.aist.cinema.application.repository.AccountRepository;
import az.aist.cinema.application.repository.RefreshTokenRepository;
import az.aist.cinema.application.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${security.secret.refreshTokenDurationMs}")
    private int refreshTokenDurationMs;

    private final AccountRepository accountRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshTokenEnt createRefreshToken(String accountUuid) {
        RefreshTokenEnt refreshTokenEnt = new RefreshTokenEnt();

        refreshTokenEnt.setAccount(accountRepository.findByUsername(accountUuid).get());
        refreshTokenEnt.setExpiryDate(LocalDateTime.from(Instant.now().plusSeconds(refreshTokenDurationMs)));
        refreshTokenEnt.setToken(UUID.randomUUID().toString());

        return refreshTokenRepository.save(refreshTokenEnt);
    }

    @Override
    public Optional<RefreshTokenEnt> findByToken(String refreshToken) {
        return refreshTokenRepository.findByToken(refreshToken);
    }


    public RefreshTokenEnt verifyExpiration(RefreshTokenEnt token) {
        if (token.getExpiryDate().compareTo(LocalDateTime.from(Instant.now())) < 0) {
            refreshTokenRepository.delete(token);
            throw new AuthRequestExcetion(401,"REFRESH-TOKEN-EXPIRED", "Refresh token was expired. Please make a new signin request");
        }
        return token;
    }
}
