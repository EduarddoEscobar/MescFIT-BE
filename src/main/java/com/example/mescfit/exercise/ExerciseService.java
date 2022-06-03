package com.example.mescfit.exercise;

import com.example.mescfit.exceptions.NotFoundException;
import com.example.mescfit.model.Exercise;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public Exercise saveExercise(String name, String description, MultipartFile file) throws IOException {
        Exercise exercise = new Exercise(name, description, file.getContentType(), file.getBytes());
        return this.exerciseRepository.save(exercise);
    }

    public Exercise getExercise(Integer id) {
        return this.exerciseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Exercise with id %s does was not found", id)));
    }

    public List<Exercise> getExercises(){
        return this.exerciseRepository.findAll();
    }
}
