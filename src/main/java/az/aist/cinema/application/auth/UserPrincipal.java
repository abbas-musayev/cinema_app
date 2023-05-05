package az.aist.cinema.application.auth;

import az.aist.cinema.application.entity.AccountEnt;
import az.aist.cinema.application.entity.AuthorityEnt;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private AccountEnt account;

    public UserPrincipal(AccountEnt account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return mapToGrantedAuthority(account.getAuthorities());
    }

    @Override
    public String getPassword() {
        return String.valueOf(account.getPassword());
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //Todo:Deyisilecek bura
    @Override
    public boolean isEnabled() {
        return true;
    }

    private Set<GrantedAuthority> mapToGrantedAuthority(Set<AuthorityEnt> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole().name())).collect(Collectors.toSet());
    }
}


