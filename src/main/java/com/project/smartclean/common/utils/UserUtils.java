package com.project.smartclean.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public final class UserUtils {

    public static String getUserId() {
        SecurityContext sc = SecurityContextHolder.getContext();
        Authentication authentication = sc.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal.getClass().equals(String.class)) {
            return null;
        } else {
            UserDetails userDetails = (UserDetails) principal;
            return userDetails.getUsername();
        }
    }

    public static UserDetails getUserDetails() {
        SecurityContext sc = SecurityContextHolder.getContext();
        Authentication authentication = sc.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal.getClass().equals(String.class)) {
            return null;
        } else {
            UserDetails userDetails = (UserDetails) principal;
            return userDetails;
        }
    }

}
