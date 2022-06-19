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
    private final ExerciseConverter converter;

    public Exercise addExercise(String name, String description, MultipartFile file) throws IOException {
        Exercise exercise = new Exercise(name, description, file.getContentType(), file.getBytes());
        return this.exerciseRepository.save(exercise);
    }

    public Exercise getExercise(Long id) {
        return this.exerciseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Exercise with id %s does was not found", id)));
    }

    // TODO: Remove once getting rid of templates
    public List<Exercise> getExercises() {
        return exerciseRepository.findAll();
    }

    public List<ExerciseDTO> getAllExercises() {
        List<ExerciseDTO> exercises = exerciseRepository.findAllWithoutVideo();
        exercises.forEach((exerciseDTO) ->
                exerciseDTO.setCategories(exerciseCategoryService.getAllCategoriesForExercise(exerciseDTO.getId()))
        );
        return exercises;
    }

}
