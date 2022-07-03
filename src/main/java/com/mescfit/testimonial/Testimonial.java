package com.mescfit.testimonial;

import com.mescfit.userProfiles.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "testimonials")
public class Testimonial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testimonial_id")
    private Long testimonialId;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "user_id", unique = true)
    private UserProfile author;

    @Column(name = "testimonial", nullable = false, length = 1500)
    private String testimonial;

    public Testimonial(UserProfile author, String testimonial) {
        this.author = author;
        this.testimonial = testimonial;
    }
}
