package com.example.mescfit.model;

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
    private Integer testimonial_id;

    @Column(name = "first_name", nullable = false, length = 32)
    private String first_name;

    @Column(name = "last_initial", length = 3)
    private String last_initial;

    @Column(name = "testimonial", nullable = false, length = 1500)
    private String testimonial;
}
