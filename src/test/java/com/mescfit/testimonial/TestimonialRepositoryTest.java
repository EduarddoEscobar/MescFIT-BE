package com.mescfit.testimonial;

import com.mescfit.userProfiles.UserProfile;
import com.mescfit.userProfiles.UserProfileRepository;
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
    @Autowired
    private UserProfileRepository userProfileRepository;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldReturnAListOfAllTestimonialsWithTheSpecificName() {
        // given
        UserProfile author = new UserProfile("Bob", "Foo", null);
        userProfileRepository.save(author);

        Testimonial testimonial = new Testimonial(
                author,
                "Testimonial"
        );

        underTest.save(testimonial);
        // when
        List<Testimonial> result = underTest.findAllByFirstName("Bob");

        // then
        List<Testimonial> expected = List.of(testimonial);
        assertThat(result).isEqualTo(expected);
    }
}