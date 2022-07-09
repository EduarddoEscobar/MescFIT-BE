package com.mescfit.exercise;

import com.mescfit.exerciseCategory.ExerciseCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExerciseConverter {

    private final ExerciseCategoryService exerciseCategoryService;

    public ExerciseDTO convertFromExercise(Exercise exercise) {
        List<String> categories = exerciseCategoryService.getAllCategoriesForExercise(exercise.getExerciseId());
        return new ExerciseDTO(
                exercise.getExerciseName(),
                exercise.getDescription(),
                exercise.getExerciseThumbnailLink(),
                exercise.getExerciseVideoLink(),
                categories
        );
    }

    public ExerciseDTO convertFromExerciseAndCategories(Exercise exercise, List<String> categories) {
        return new ExerciseDTO(
                exercise.getExerciseName(),
                exercise.getDescription(),
                exercise.getExerciseThumbnailLink(),
                exercise.getExerciseVideoLink(),
                categories
        );
    }

    public Exercise convertToExercise(ExerciseDTO exerciseDTO) {
        return new Exercise(
                exerciseDTO.getExerciseName(),
                exerciseDTO.getDescription(),
                exerciseDTO.getExerciseThumbnailLink(),
                exerciseDTO.getExerciseVideoLink()
        );
    }
}
