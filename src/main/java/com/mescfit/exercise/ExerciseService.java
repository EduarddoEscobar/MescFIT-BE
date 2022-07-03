package com.mescfit.exercise;

import com.mescfit.exceptions.NotFoundException;
import com.mescfit.exercise.category.ExerciseCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseCategoryService exerciseCategoryService;

    public Exercise addExercise(String name, String description, MultipartFile file) throws IOException {
        Exercise exercise = new Exercise(name, description, null);
        return this.exerciseRepository.save(exercise);
    }

    public Exercise getExerciseById(Long id) {
        return this.exerciseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Exercise with id %s does was not found", id)));
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise removeExercise(Long id) {
        Exercise exerciseToDelete =  getExerciseById(id);
        exerciseRepository.delete(exerciseToDelete);
        return exerciseToDelete;
    }

    public Exercise updateExercise(Exercise exercise, Long id) {
        Exercise exerciseToUpdate = getExerciseById(id);
        if(exercise.getExerciseName() != null) exerciseToUpdate.setExerciseName(exercise.getExerciseName());
        if(exercise.getDescription() != null) exerciseToUpdate.setDescription(exercise.getDescription());
        return exerciseRepository.save(exerciseToUpdate);
    }
}
