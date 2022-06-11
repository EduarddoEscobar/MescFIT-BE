package com.example.mescfit.testimonial;

import com.example.mescfit.model.Testimonial;
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
        Testimonial testimonial = new Testimonial(1,
                "Bob",
                "M",
                "Testimonial"
        );
        given(testimonialRepository.findById(1))
                .willReturn(Optional.of(testimonial));
        // when
        Testimonial result = underTest.getTestimonialById(1);

        // then
        assertThat(result).isEqualTo(testimonial);
    }

    @Test
    void willThrowWhenThereIsNoTestimonialWithId() {
        // given
        given(testimonialRepository.findById(1))
                .willReturn(Optional.empty());
        // when
        // then
        assertThatThrownBy(() -> underTest.getTestimonialById(1))
                .hasMessageContaining("Testimonial with id: 1 was not found");
    }

    @Test
    void canAddTestimonial() {
        // given
        Testimonial testimonial = new Testimonial(1,
                "Bob",
                "M",
                "Testimonial"
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
        Testimonial testimonial = new Testimonial(1,
                "Bob",
                "M",
                "Testimonial"
        );
        Testimonial newTestimonial = new Testimonial(1,
                "John",
                "M",
                "Testimonial"
        );
        given(testimonialRepository.findById(1))
                .willReturn(Optional.of(testimonial));
        // when
        underTest.updateTestimonial(newTestimonial, 1);

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
        Testimonial testimonial = new Testimonial(1,
                "Bob",
                "M",
                "Testimonial"
        );
        given(testimonialRepository.findById(1))
                .willReturn(Optional.of(testimonial));
        // when
        Testimonial result = underTest.deleteTestimonial(1);

        // then
        verify(testimonialRepository).deleteById(1);
        assertThat(result).isEqualTo(testimonial);
    }
}