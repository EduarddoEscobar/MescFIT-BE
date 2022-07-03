package com.mescfit.testimonial;

import com.mescfit.userProfiles.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TestimonialServiceTest {
    @Mock
    private TestimonialRepository testimonialRepository;
    private TestimonialService underTest;

    @BeforeEach
    void setUp() {
        underTest = new TestimonialService(testimonialRepository);
    }

    @Test
    void canGetAllTestimonials() {
        // when
        underTest.getAllTestimonials();
        //then
        verify(testimonialRepository).findAll();
    }

    @Test
    void canGetTestimonialById() {
        // given
        Testimonial testimonial = new Testimonial(
                new UserProfile(),
                "testimonial"
        );
        given(testimonialRepository.findById(1L))
                .willReturn(Optional.of(testimonial));
        // when
        Testimonial result = underTest.getTestimonialById(1L);

        // then
        assertThat(result).isEqualTo(testimonial);
    }

    @Test
    void willThrowWhenThereIsNoTestimonialWithId() {
        // given
        given(testimonialRepository.findById(1L))
                .willReturn(Optional.empty());
        // when
        // then
        assertThatThrownBy(() -> underTest.getTestimonialById(1L))
                .hasMessageContaining("Testimonial with id: 1 was not found");
    }

    @Test
    void canAddTestimonial() {
        // given
        Testimonial testimonial = new Testimonial(
                new UserProfile(),
                "testimonial"
        );

        // when
        underTest.addTestimonial(testimonial);

        // then
        ArgumentCaptor<Testimonial> testimonialArgumentCaptor =
                ArgumentCaptor.forClass(Testimonial.class);
        verify(testimonialRepository)
                .save(testimonialArgumentCaptor.capture());

        Testimonial capturedTestimonial = testimonialArgumentCaptor.getValue();
        assertThat(capturedTestimonial).isEqualTo(testimonial);
    }

    @Test
    void canUpdateTestimonial() {
        // given
        UserProfile user = new UserProfile();
        Testimonial testimonial = new Testimonial(
                user,
                "testimonial"
        );
        Testimonial newTestimonial = new Testimonial(
                user,
                "new testimonial"
        );
        given(testimonialRepository.findById(1L))
                .willReturn(Optional.of(testimonial));
        // when
        underTest.updateTestimonial(newTestimonial, 1L);

        // then
        ArgumentCaptor<Testimonial> testimonialArgumentCaptor =
                ArgumentCaptor.forClass(Testimonial.class);
        verify(testimonialRepository)
                .save(testimonialArgumentCaptor.capture());
        Testimonial capturedTestimonial = testimonialArgumentCaptor.getValue();
        assertThat(capturedTestimonial).isEqualTo(newTestimonial);
    }

    @Test
    void canDeleteTestimonial() {
        // given
        Testimonial testimonial = new Testimonial(
                new UserProfile(),
                "testimonial"
        );
        given(testimonialRepository.findById(1L))
                .willReturn(Optional.of(testimonial));
        // when
        Testimonial result = underTest.deleteTestimonial(1L);

        // then
        verify(testimonialRepository).deleteById(1L);
        assertThat(result).isEqualTo(testimonial);
    }
}