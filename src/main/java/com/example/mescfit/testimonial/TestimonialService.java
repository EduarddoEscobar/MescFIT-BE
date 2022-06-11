package com.example.mescfit.testimonial;

import com.example.mescfit.exceptions.NotFoundException;
import com.example.mescfit.model.Testimonial;
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

    public Testimonial getTestimonialById(Integer id) {
        return testimonialRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Testimonial with id: %s was not found", id)));
    }

    public Testimonial addTestimonial(Testimonial testimonial) {
        return testimonialRepository.save(testimonial);
    }

    public Testimonial updateTestimonial(Testimonial testimonial, Integer id) {
        Testimonial testimonialToUpdate = getTestimonialById(id);
        if(testimonial.getFirst_name() != null) testimonialToUpdate.setFirst_name(testimonial.getFirst_name());
        if(testimonial.getLast_initial() != null) testimonialToUpdate.setLast_initial(testimonial.getLast_initial());
        if(testimonial.getTestimonial() != null) testimonialToUpdate.setTestimonial(testimonial.getTestimonial());
        return testimonialRepository.save(testimonialToUpdate);
    }

    public Testimonial deleteTestimonial(Integer id) {
        Testimonial testimonial = getTestimonialById(id);
        testimonialRepository.deleteById(id);
        return testimonial;
    }
}
