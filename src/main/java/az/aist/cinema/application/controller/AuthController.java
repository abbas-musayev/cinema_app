package az.aist.cinema.application.controller;

import az.aist.cinema.application.auth.JwtService;
import az.aist.cinema.application.auth.UserPrincipal;
import az.aist.cinema.application.dto.auth.JwtResponse;
import az.aist.cinema.application.dto.auth.LoginRequest;
import az.aist.cinema.application.dto.auth.RefreshTokenRequest;
import az.aist.cinema.application.dto.auth.TokenRefreshResponse;
import az.aist.cinema.application.entity.RefreshTokenEnt;
import az.aist.cinema.application.exception.AuthRequestExcetion;
import az.aist.cinema.application.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

        String jwt = jwtService.generateToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshTokenEnt refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

        return ResponseEntity.ok(JwtResponse.builder()
                .token(jwt)
                .refreshToken(refreshToken.getToken())
                .type("Bearer")
                .roles(roles)
                .build());
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@RequestBody RefreshTokenRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshTokenEnt::getAccount)
                .map(user -> {
                    String token = jwtService.generateToken(new UserPrincipal(user));
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new AuthRequestExcetion(401,"TOKEN-NOT-FOUND",
                        "Refresh token is not in database!"));
    }

}
