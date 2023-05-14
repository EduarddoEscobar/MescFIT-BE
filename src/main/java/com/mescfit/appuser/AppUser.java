package com.mescfit.appuser;

import com.mescfit.appuser.roles.AppUserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class AppUser implements UserDetails {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password", nullable = true)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "fk_user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_role_id", referencedColumnName = "role_id")}
    )
    private Set<AppUserRole> roles;

    @Column(name = "locked")
    private Boolean locked = false;

    @Column(name = "enabled")
    private Boolean enabled = true;

    @Column(name = "two_factor_authentication")
    private Boolean tfa = false;

    @Column(name = "subscribed")
    private Boolean subscribed = false;

    @Column(name = "profile_image_link")
    private String profileImageLink;


    public AppUser(Long userId, String firstName, String lastName, String email, String password, AppUserRole roles) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = Collections.singleton(roles);
    }

    public AppUser(String firstName, String lastName, String email, String password, AppUserRole roles, String profileImageLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = Collections.singleton(roles);
        this.profileImageLink = profileImageLink;
    }

    public AppUser(Long userId, String firstName, String lastName, String email, String phoneNumber, Set<AppUserRole> roles, Boolean tfa, Boolean subscribed) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.tfa = tfa;
        this.subscribed = subscribed;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(AppUserRole::getRoleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
