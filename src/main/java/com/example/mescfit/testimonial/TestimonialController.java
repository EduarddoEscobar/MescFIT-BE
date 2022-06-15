package com.example.mescfit.testimonial;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testimonials")
public class TestimonialController {
    private final TestimonialService testimonialService;

    public TestimonialController(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }

    @GetMapping
    public List<Testimonial> findAllTestimonials(){
        return testimonialService.getAllTestimonials();
    }

    @GetMapping(value = {"/{id}"})
    public Testimonial findTestimonialById(@PathVariable Long id){
        return testimonialService.getTestimonialById(id);
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
