package com.mescfit.testimonial;

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
    private Long id;

    @Column(name = "first_name", nullable = false, length = 32)
    private String firstName;

    @Column(name = "last_initial", length = 3)
    private String lastInitial = "N/A";

    @Column(name = "testimonial", nullable = false, length = 1500)
    private String testimonial;

    public Testimonial(String firstName, String lastInitial, String testimonial) {
        this.firstName = firstName;
        this.lastInitial = lastInitial;
        this.testimonial = testimonial;
    }
}
