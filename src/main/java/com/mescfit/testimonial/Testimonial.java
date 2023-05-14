package com.mescfit.testimonial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "testimonials")
public class Testimonial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testimonial_id")
    private Long testimonialId;

    @Column(name = "author_name", nullable = false)
    private String firstName;

    @Column(name = "author_initial")
    private String lastInitial;

    @Column(name = "author_email", nullable = false)
    private String email;

    @Column(name = "testimonial", nullable = false, length = 1500)
    private String testimonial;
}
