package com.mescfit.testimonial;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/testimonials")
@AllArgsConstructor
public class TestimonialController {
    private final TestimonialService testimonialService;

    @GetMapping
    public List<Testimonial> getAllTestimonials(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) Long id) {
        if (firstName != null) return testimonialService.getTestimonialsByFirstName(firstName);
        if (id != null) return List.of(testimonialService.getTestimonialById(id));
        return testimonialService.getAllTestimonials();
    }

    @PostMapping
    public ResponseEntity<Testimonial> createNewTestimonial(@RequestBody Testimonial testimonial) {
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/testimonials")
                .toUriString());
        return ResponseEntity.created(uri).body(testimonialService.addTestimonial(testimonial));
    }

    @PutMapping(value = {"/{id}"})
    public ResponseEntity<Testimonial> updateTestimonial(@RequestBody Testimonial testimonial, @PathVariable Long id) {
        return ResponseEntity.ok().body(testimonialService.updateTestimonial(testimonial, id));
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Testimonial> deleteTestimonial(@PathVariable Long id) {
        return ResponseEntity.ok().body(testimonialService.deleteTestimonial(id));
    }
}
