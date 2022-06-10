package com.example.mescfit.testimonial;

import com.example.mescfit.model.Testimonial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class TestimonialRepositoryTest {
    @Autowired
    private TestimonialRepository underTest;

    @Test
    void saveFunctionWorks() {
        // given
        Testimonial testimonial = new Testimonial(1,
                "Bob",
                "M",
                "Testimonial"
        );

        // when
        Testimonial result = underTest.save(testimonial);

        // then
        assertThat(result).isEqualTo(testimonial);
    }
}