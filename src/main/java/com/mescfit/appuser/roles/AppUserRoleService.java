package com.mescfit.appuser.roles;

import com.mescfit.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserRoleService {

    private final AppUserRoleRepository appUserRoleRepository;

    public AppUserRole getRoleByName(String name) {
        return appUserRoleRepository.findByRoleName(name)
                .orElseThrow(() -> new NotFoundException(String.format("Role with name %s not found", name)));
    }
}
