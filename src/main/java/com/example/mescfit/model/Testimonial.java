package com.example.mescfit.model;

import javax.persistence.*;

@Entity
@Table(name = "testimonials")
public class Testimonial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer testimonial_id;

    private String first_name;
    private String last_initial;
    private String testimonial;
}
