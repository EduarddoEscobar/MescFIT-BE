package com.example.mescfit.testimonial;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.mescfit.model.Testimonial;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TestimonialController.class})
@ExtendWith(SpringExtension.class)
class TestimonialControllerTest {
    @Autowired
    private TestimonialController testimonialController;

    @MockBean
    private TestimonialService testimonialService;

    @Test
    void testUpdateTestimonial() throws Exception {
        // given
        Testimonial testimonial = new Testimonial(1,
                "Jane",
                "L",
                "Testimonial",
                false
        );
        Testimonial testimonial1 = new Testimonial(1,
                "Jane",
                "I",
                "Testimonial",
                false
        );

        // when
        when(this.testimonialService.updateTestimonial((Testimonial) any(), (Integer) any())).thenReturn(testimonial1);
        String content = (new ObjectMapper()).writeValueAsString(testimonial1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/testimonials/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        // then
        MockMvcBuilders.standaloneSetup(this.testimonialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"testimonial_id\":1,\"first_name\":\"Jane\",\"last_initial\":\"I\",\"testimonial\":\"Testimonial\",\"anonymous\":false}"));
    }

    @Test
    void testCreateNewTestimonial() throws Exception {
        // given
        Testimonial testimonial = new Testimonial(1,
                "Jane",
                "L",
                "Testimonial",
                false
        );
        Testimonial testimonial1 = new Testimonial(1,
                "Jane",
                "I",
                "Testimonial",
                false
        );

        // when
        when(this.testimonialService.addTestimonial((Testimonial) any())).thenReturn(testimonial);
        String content = (new ObjectMapper()).writeValueAsString(testimonial1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/testimonials")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // then
        MockMvcBuilders.standaloneSetup(this.testimonialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"testimonial_id\":1,\"first_name\":\"Jane\",\"last_initial\":\"L\",\"testimonial\":\"Testimonial\",\"anonymous\":false}"));
    }

    @Test
    void testDeleteTestimonial() throws Exception {
        // given
        Testimonial testimonial = new Testimonial(1,
                "Jane",
                "L",
                "Testimonial",
                false
        );

        // when
        when(this.testimonialService.deleteTestimonial((Integer) any())).thenReturn(testimonial);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/testimonials/{id}", 1);

        // then
        MockMvcBuilders.standaloneSetup(this.testimonialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"testimonial_id\":1,\"first_name\":\"Jane\",\"last_initial\":\"L\",\"testimonial\":\"Testimonial\",\"anonymous\":false}"));
    }

    @Test
    void testFindAllTestimonials() throws Exception {
        // given
        // when
        when(this.testimonialService.getAllTestimonials()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/testimonials");

        // then
        MockMvcBuilders.standaloneSetup(this.testimonialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testFindTestimonialById() throws Exception {
        // given
        Testimonial testimonial = new Testimonial(1,
                "Jane",
                "L",
                "Testimonial",
                false
        );

        // when
        when(this.testimonialService.getTestimonialById((Integer) any())).thenReturn(testimonial);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/testimonials/{id}", 1);

        // then
        MockMvcBuilders.standaloneSetup(this.testimonialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"testimonial_id\":1,\"first_name\":\"Jane\",\"last_initial\":\"L\",\"testimonial\":\"Testimonial\",\"anonymous\":false}"));
    }
}

