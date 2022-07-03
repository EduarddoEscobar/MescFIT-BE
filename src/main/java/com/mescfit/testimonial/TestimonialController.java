package com.mescfit.testimonial;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/testimonials")
@AllArgsConstructor
public class TestimonialController {
    private final TestimonialService testimonialService;

    @GetMapping
    public List<Testimonial> getAllTestimonials(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) Long id){
        if(firstName != null) return testimonialService.getTestimonialsByFirstName(firstName);
        if(id != null) return List.of(testimonialService.getTestimonialById(id));
        return testimonialService.getAllTestimonials();
    }

    @PostMapping
    public Testimonial createNewTestimonial(@RequestBody Testimonial testimonial){
        return testimonialService.addTestimonial(testimonial);
    }

    @PutMapping(value = {"/{id}"})
    public Testimonial updateTestimonial(@RequestBody Testimonial testimonial, @PathVariable Long id){
        return testimonialService.updateTestimonial(testimonial, id);
    }

    @DeleteMapping(value = {"/{id}"})
    public Testimonial deleteTestimonial(@PathVariable Long id){
        return testimonialService.deleteTestimonial(id);
    }
}
