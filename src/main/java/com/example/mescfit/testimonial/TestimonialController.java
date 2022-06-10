package com.example.mescfit.testimonial;

import com.example.mescfit.model.Testimonial;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testimonials")
public class TestimonialController {
    private final TestimonialService testimonialService;

    public TestimonialController(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }

    @GetMapping(value = {"", "/"})
    public List<Testimonial> findAllTestimonials(){
        return testimonialService.getAllTestimonials();
    }

    @GetMapping(value = {"/{id}"})
    public Testimonial findTestimonialById(@PathVariable Integer id){
        return testimonialService.getTestimonialById(id);
    }

    @PostMapping(value = {"", "/"})
    public Testimonial createNewTestimonial(@RequestBody Testimonial testimonial){
        return testimonialService.saveTestimonial(testimonial);
    }

    @PutMapping(value = {"/{id}"})
    public Testimonial updateTestimonial(@RequestBody Testimonial testimonial, @PathVariable Integer id){
        return testimonialService.updateTestimonial(testimonial, id);
    }

    @DeleteMapping(value = {"/{id}"})
    public Testimonial deleteTestimonial(@PathVariable Integer id){
        return testimonialService.deleteTestimonial(id);
    }
}
