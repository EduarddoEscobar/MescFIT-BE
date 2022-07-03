package com.mescfit.testimonial;

import com.mescfit.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TestimonialService {
    private final TestimonialRepository testimonialRepository;

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
        if(testimonial.getTestimonial() != null) testimonialToUpdate.setTestimonial(testimonial.getTestimonial());
        return testimonialRepository.save(testimonialToUpdate);
    }

    public Testimonial deleteTestimonial(Long id) {
        Testimonial testimonial = getTestimonialById(id);
        testimonialRepository.deleteById(id);
        return testimonial;
    }

    public List<Testimonial> getTestimonialsByFirstName(String firstName) {
        return testimonialRepository.findAllByFirstName(firstName);
    }
}
