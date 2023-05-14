package com.mescfit.auth;

import com.mescfit.appuser.AppUser;
import com.mescfit.appuser.roles.AppUserRole;

import java.util.Set;

public record RegistrationResponse(Long userId,
                                   String firstName,
                                   String lastName,
                                   String email,
                                   Set<AppUserRole> roles,
                                   String refreshToken,
                                   String accessToken) {
    public RegistrationResponse(String refreshToken, String accessToken) {
        this(null, null, null, null, null, refreshToken, accessToken);
    }

    public RegistrationResponse(AppUser user, String refreshToken, String accessToken) {
        this(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRoles(),
                refreshToken,
                accessToken
        );
    }
}
