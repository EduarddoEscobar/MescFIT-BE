package com.mescfit.exerciseCategory;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exercise-categories")
@AllArgsConstructor
public class ExerciseCategoryController {

    private final ExerciseCategoryService exerciseCategoryService;

    @GetMapping
    public List<ExerciseCategoryKey> getAllExercises() {
        return exerciseCategoryService.getAllCategories();
    }

}
