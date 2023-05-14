package com.mescfit.testimonial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {
    @Query(value = "SELECT t FROM Testimonial t " +
            "WHERE t.firstName = ?1")
    List<Testimonial> findAllByFirstName(String name);
}
