package com.mescfit.exercise.category;

import com.mescfit.exercise.Exercise;
import com.mescfit.exercise.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/EC")
@AllArgsConstructor
public class ECController {

    private final ExerciseCategoryService exerciseCategoryService;
    private final ExerciseService exerciseService;

    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

//    TODO: Fix this method so that is correctly shows the exercise name and all its categories
    @PostMapping("/{id}")
    public Exercise addEC(@RequestBody List<String> categories, @PathVariable Long id) {
        Exercise exercise = exerciseService.getExerciseById(id);
        List<String> categoryNames = exerciseCategoryService.addCategoriesToExercise(exercise, categories)
                .stream()
                .map((category) -> category.getId().getCategory().getCategoryName()).toList();
        return null;
    }
}
