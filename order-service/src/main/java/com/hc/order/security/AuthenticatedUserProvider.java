package com.hc.order.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserProvider {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getUsername() {
        Object principal = getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails userDetails) {
            return userDetails.getUsername();
        }
        return principal.toString();
    }

    public Long getUserId() {
        Object principal = getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails userDetails) {
            return userDetails.getUserId();
        }
        throw new RuntimeException("User ID not found in authentication context");
    }

    public String getCurrentToken() {
        Authentication authentication = getAuthentication();
        if (authentication == null || !(authentication.getCredentials() instanceof String token)) {
            throw new RuntimeException("JWT Token not found in SecurityContext");
        }
        return token;
    }
}
