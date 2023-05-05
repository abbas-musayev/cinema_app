package az.aist.cinema.application.auth;

import az.aist.cinema.application.entity.AccountEnt;
import az.aist.cinema.application.entity.AuthorityEnt;
import az.aist.cinema.application.entity.RefreshTokenEnt;
import az.aist.cinema.application.repository.AccountRepository;
import az.aist.cinema.application.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {

    private Key key;
    private final AccountRepository accountRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${security.secret.key}")
    private String secretKey;

    @Value("${security.secret.jwtExpirationMs}")
    private int jwtExpirationMs;

    @PostConstruct
    public void init() {
        byte[] keyBytes;
        keyBytes = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserPrincipal user) {
        log.info("Issue JWT token to {}", user);
        final JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuedAt(new Date())
                .claim("username", user.getUsername())
//                .claim("userId", user.getId())
//                .claim("role", user.getAuthorities().stream().map(authority -> authority.getRole().name()).collect(
//                        Collectors.toList()))
                .claim("role", user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setExpiration(Date.from(Instant.now().plusSeconds(jwtExpirationMs)))
                .setHeader(Map.of("type", "JWT"))
                .signWith(key, SignatureAlgorithm.HS256);
        return jwtBuilder.compact();
    }
    
    public Authentication getAuthenticationBearerByClaims(Claims claims){
        List<?> roles = claims.get("role", List.class);

        List<SimpleGrantedAuthority> authorityList = roles.stream()
                .map(a -> new SimpleGrantedAuthority(a.toString()))
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(claims,"",authorityList);
    }


    public Authentication getAuthenticationBearerByUser(AccountEnt user){
        List<?> roles = user.getAuthorities().stream()
                .map(AuthorityEnt::getRole)
                .collect(Collectors.toList());

        List<SimpleGrantedAuthority> authorityList = roles.stream()
                .map(a -> new SimpleGrantedAuthority(a.toString()))
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(user,"",authorityList);
    }

}
