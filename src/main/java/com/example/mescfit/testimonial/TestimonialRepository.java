package com.example.mescfit.testimonial;

import com.example.mescfit.model.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, Integer> {
    @Query("SELECT t FROM testimonials t WHERE t.first_name = ?1")
    List<Testimonial> findAllByFirstName(String name);
}
