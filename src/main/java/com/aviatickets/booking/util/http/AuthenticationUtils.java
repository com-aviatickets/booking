package com.aviatickets.booking.util.http;

import com.aviatickets.booking.exception.UserNotAuthenticated;
import com.aviatickets.booking.model.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtils {

    public static Long getUserIdFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            return ((CustomUserDetails) authentication.getPrincipal()).getId();
        }
        throw new UserNotAuthenticated("User not authenticated");
    }
}
