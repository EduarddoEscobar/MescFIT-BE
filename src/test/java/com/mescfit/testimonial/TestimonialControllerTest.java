package com.mescfit.testimonial;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TestimonialController.class})
@ExtendWith(SpringExtension.class)
class TestimonialControllerTest {
    @Autowired
    private TestimonialController testimonialController;

    @MockBean
    private TestimonialService testimonialService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testUpdateTestimonial() throws Exception {
        // given
        Testimonial testimonial = new Testimonial(
                null,
                "foo",
                "B",
                "something@email.com",
                "Updated Testimonial"
        );

        // when
        when(this.testimonialService.updateTestimonial((Testimonial) any(), (Long) any())).thenReturn(testimonial);
        String content = (new ObjectMapper()).writeValueAsString(testimonial);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/testimonials/{id}", 1)
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
                                "{\"testimonialId\":null,\"firstName\":\"foo\",\"lastInitial\":\"B\",\"email\":\"something@email.com\",\"testimonial\":\"Updated Testimonial\"}"));
    }

    @Test
    void testCreateNewTestimonial() throws Exception {
        // given
        Testimonial testimonial = new Testimonial(
                null,
                "foo",
                "B",
                "something@email.com",
                "testimonial"
        );

        // when
        when(this.testimonialService.addTestimonial((Testimonial) any())).thenReturn(testimonial);
        String content = (new ObjectMapper()).writeValueAsString(testimonial);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/testimonials")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // then
        MockMvcBuilders.standaloneSetup(this.testimonialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"testimonialId\":null,\"firstName\":\"foo\",\"lastInitial\":\"B\",\"email\":\"something@email.com\",\"testimonial\":\"testimonial\"}"));
    }

    @Test
    void testDeleteTestimonial() throws Exception {
        // given
        Testimonial testimonial = new Testimonial(
                null,
                "foo",
                "B",
                "something@email.com",
                "testimonial"
        );

        // when
        when(this.testimonialService.deleteTestimonial((Long) any())).thenReturn(testimonial);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/testimonials/{id}", 1);

        // then
        MockMvcBuilders.standaloneSetup(this.testimonialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"testimonialId\":null,\"firstName\":\"foo\",\"lastInitial\":\"B\",\"email\":\"something@email.com\",\"testimonial\":\"testimonial\"}"));

    }

    @Test
    void testFindAllTestimonials() throws Exception {
        // given
        // when
        when(this.testimonialService.getAllTestimonials()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/testimonials");

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
        Testimonial testimonial = new Testimonial(
                1L,
                "foo",
                "B",
                "something@email.com",
                "testimonial"
        );

        // when
        when(this.testimonialService.getTestimonialById((Long) any())).thenReturn(testimonial);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/testimonials?id=1");

        // then
        MockMvcBuilders.standaloneSetup(this.testimonialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"testimonialId\":1,\"firstName\":\"foo\",\"lastInitial\":\"B\",\"email\":\"something@email.com\",\"testimonial\":\"testimonial\"}]"
                        ));
    }

    @Test
    void testFindTestimonialByFirstName() throws Exception {
        // given
        Testimonial testimonial = new Testimonial(
                1L,
                "foo",
                "B",
                "something@email.com",
                "testimonial"
        );

        // when
        when(this.testimonialService.getTestimonialsByFirstName((String) any())).thenReturn(List.of(testimonial));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/testimonials?firstName=foo");

        // then
        MockMvcBuilders.standaloneSetup(this.testimonialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"testimonialId\":1,\"firstName\":\"foo\",\"lastInitial\":\"B\",\"email\":\"something@email.com\",\"testimonial\":\"testimonial\"}]"
                        ));
    }
}

