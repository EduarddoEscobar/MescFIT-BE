package com.example.mescfit.testimonial;

import com.example.mescfit.model.Testimonial;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class TestimonialRepositoryTest {
    @Autowired
    private TestimonialRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldReturnAListOfAllTestimonialsWithTheSpecificName() {
        // given
        Testimonial testimonial = new Testimonial(1,
                "Bob",
                "M",
                "Testimonial",
                false
        );

        underTest.save(testimonial);
        // when
        List<Testimonial> result = underTest.findAllByFirstName("Bob");

        // then
        List<Testimonial> expected = List.of(testimonial);
        assertThat(result).isEqualTo(expected);
    }
}