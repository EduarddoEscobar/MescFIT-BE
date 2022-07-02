package com.mescfit.userProfiles;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "profile_image_link")
    private String profileImageLink;

    public UserProfile(String firstName, String lastName, String profileImageLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImageLink = profileImageLink;
    }
}
