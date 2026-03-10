package kz.bi.service.utils;

import kz.bi.dao.entity.user.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class SecurityUtils {
    public  Authentication getCurrentAuthentication() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Assert.notNull(authentication, "Authentication is required");

        return authentication;
    }
    public UserEntity getCurrentUser() {
        var currentAuthentication = this.getCurrentAuthentication();
        return (UserEntity) currentAuthentication.getPrincipal();
    }
}
