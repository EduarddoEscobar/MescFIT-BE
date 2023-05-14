package com.mescfit.appuser;

import com.mescfit.appuser.roles.AppUserRole;

import java.util.Set;
import java.util.stream.Collectors;

public record AppUserDTO(String firstName,
                         String lastName,
                         String email,
                         String phoneNumber,
                         Set<String> roles,
                         Boolean tfa,
                         Boolean subscribed,
                         Boolean confirmed) {
    public AppUserDTO(AppUser appUser) {
        this(
                appUser.getFirstName(),
                appUser.getLastName(),
                appUser.getEmail(),
                appUser.getPhoneNumber(),
                appUser.getRoles().stream().map(AppUserRole::getRoleName).collect(Collectors.toSet()),
                appUser.getTfa(),
                appUser.getSubscribed(),
                appUser.isEnabled());
    }

    public static AppUser dtoToAppUser(Long userId, AppUserDTO user) {
        return new AppUser(
                userId,
                user.firstName,
                user.lastName,
                user.email,
                user.phoneNumber,
                user.roles.stream().map(AppUserRole::new).collect(Collectors.toSet()),
                user.tfa,
                user.subscribed);
    }
    // TODO: Add profile picture link in this record
}
