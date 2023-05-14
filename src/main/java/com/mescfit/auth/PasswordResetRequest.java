package com.mescfit.auth;

public record PasswordResetRequest(String newPassword,
                                   String accessToken) {
}
