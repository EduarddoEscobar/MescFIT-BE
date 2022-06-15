package com.mescfit.testimonial;

import com.mescfit.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestimonialService {
    private final TestimonialRepository testimonialRepository;

    public TestimonialService(TestimonialRepository testimonialRepository) {
        this.testimonialRepository = testimonialRepository;
    }

    public List<Testimonial> getAllTestimonials() {
        return testimonialRepository.findAll();
    }

    public Testimonial getTestimonialById(Long id) {
        return testimonialRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Testimonial with id: " + id + " was not found"));
    }

    public Testimonial addTestimonial(Testimonial testimonial) {
        return testimonialRepository.save(testimonial);
    }

    public Testimonial updateTestimonial(Testimonial testimonial, Long id) {
        Testimonial testimonialToUpdate = getTestimonialById(id);
        if(testimonial.getFirstName() != null) testimonialToUpdate.setFirstName(testimonial.getFirstName());
        if(testimonial.getLastInitial() != null) testimonialToUpdate.setLastInitial(testimonial.getLastInitial());
        if(testimonial.getTestimonial() != null) testimonialToUpdate.setTestimonial(testimonial.getTestimonial());
        return testimonialRepository.save(testimonialToUpdate);
    }

    public Testimonial deleteTestimonial(Long id) {
        Testimonial testimonial = getTestimonialById(id);
        testimonialRepository.deleteById(id);
        return testimonial;
    }
}
