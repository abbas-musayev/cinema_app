package az.aist.cinema.application.auth.auth;


import az.aist.cinema.application.auth.JwtService;
import az.aist.cinema.application.entity.AccountEnt;
import az.aist.cinema.application.exception.ErrorCodesEnum;
import az.aist.cinema.application.exception.NotFoundCustomException;
import az.aist.cinema.application.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class BasicAuthService implements AuthService {
    public static final String BASIC = "Basic ";

    private final JwtService jwtService;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Authentication> getAuthentication(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        String basic = "";

        if (authorization != null && authorization.startsWith(BASIC)) {

            basic = authorization.substring(BASIC.length());

            byte[] decode = Base64.getDecoder()
                    .decode(basic);

            String[] credentials = new String(decode).split(":");

            if (credentials.length != 2) {
                throw new RuntimeException("");
            }
            String username = credentials[0];
            String password = credentials[1];
            AccountEnt account = accountRepository.findByUsername(username)
                    .orElseThrow(() -> new NotFoundCustomException(ErrorCodesEnum.USER_NOT_FOUND,"User not found when login"));

            boolean matches = passwordEncoder.matches(password, String.valueOf(account.getPassword()));

            if (matches) {
                return Optional.of(jwtService.getAuthenticationBearerByUser(account));
            } else {
                throw new RuntimeException("USERNAME OR PASSWORD IS NOT CORRECT");
            }
        }else {
            return Optional.empty();
        }
    }
}
