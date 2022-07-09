package com.mescfit.testimonial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {
    @Query(
            value = "SELECT t.* FROM testimonials AS t " +
                    "LEFT JOIN users AS u ON t.user_id = u.user_id " +
                    "WHERE u.first_name = ?1",
            nativeQuery = true)
    List<Testimonial> findAllByFirstName(String name);
}
