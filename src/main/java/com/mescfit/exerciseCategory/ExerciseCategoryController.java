package com.mescfit.exerciseCategory;

import com.mescfit.exercise.Exercise;
import com.mescfit.exercise.ExerciseDTO;
import com.mescfit.exercise.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exercise-categories")
@AllArgsConstructor
public class ExerciseCategoryController {

    private final ExerciseCategoryService exerciseCategoryService;
    private final ExerciseService exerciseService;

    @GetMapping
    public List<ExerciseCategoryKey> getAllExercises() {
        return exerciseCategoryService.getAllCategories();
    }

    @PostMapping("/{exerciseId}")
    public ExerciseDTO addEC(@RequestBody List<String> categories, @PathVariable Long exerciseId) {
        Exercise exercise = exerciseService.getExerciseById(exerciseId);
        List<String> categoryNames = exerciseCategoryService.addCategoriesToExercise(exercise, categories)
                .stream()
                .map((category) -> category.getId().getCategory().getCategoryName())
                .toList();
        return new ExerciseDTO(exercise, categoryNames);
    }
}
