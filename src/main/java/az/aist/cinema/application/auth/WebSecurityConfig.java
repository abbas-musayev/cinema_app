package az.aist.cinema.application.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;

import static org.springframework.http.HttpMethod.*;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String SWAGGER2 = "/v2/api-docs";
    private static final String SWAGGER3 = "/v3/api-docs";
    private static final String SWAGGER_UI = "/swagger-ui/**";
    private static final String SWAGGER_HTML = "/swagger-ui.html";

    private final JwtAuthSecurityConfigurerAdapter jwtAuthSecurityConfigurerAdapter;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().disable();
        http.headers().frameOptions().sameOrigin();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(SWAGGER2).permitAll();
        http.authorizeRequests().antMatchers(SWAGGER3).permitAll();
        http.authorizeRequests().antMatchers(SWAGGER_UI).permitAll();
        http.authorizeRequests().antMatchers(SWAGGER_HTML).permitAll();
        http.authorizeRequests()
                .antMatchers("/v1/customer/**").hasAnyAuthority("ADMIN", "USER", "MODERATOR")
                .antMatchers(GET, "/v1/movie/**").hasAuthority("USER")
                .antMatchers(POST, "/v1/movie").hasAnyAuthority("ADMIN", "MODERATOR")
                .antMatchers(DELETE, "/v1/movie").hasAnyAuthority("ADMIN", "MODERATOR")
                .antMatchers(PUT, "/v1/movie").hasAnyAuthority("ADMIN", "MODERATOR")
                .antMatchers(POST, "/v1/customer").permitAll()
                .antMatchers(GET, "/v1/customers/**").hasAnyAuthority("ADMIN", "MODERATOR")
                .antMatchers(GET, "/v1/customers/search").hasAnyAuthority("ADMIN", "MODERATOR","USER")
                .antMatchers(POST, "/v1/register").permitAll()
                .antMatchers(GET, "/auth/**").permitAll();
        http.logout().disable();
        http.formLogin().disable();
        http.apply(jwtAuthSecurityConfigurerAdapter);
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        super.configure(http);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/configuration/security",
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/webjars/**");
    }
}
