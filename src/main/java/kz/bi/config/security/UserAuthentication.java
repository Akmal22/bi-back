package kz.bi.config.security;

import kz.bi.dao.entity.user.Role;
import kz.bi.dao.entity.user.UserEntity;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Getter
public class UserAuthentication extends AbstractAuthenticationToken {

    private final String username;
    private final long userId;
    private final String email;
    private final Role role;

    public UserAuthentication(UserEntity user) {
        super(List.of(new SimpleGrantedAuthority(user.getRole().name())));
        this.username = user.getUsername();
        this.userId = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }
}
