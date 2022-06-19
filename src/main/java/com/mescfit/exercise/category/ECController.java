package com.mescfit.exercise.category;

import com.mescfit.exercise.Exercise;
import com.mescfit.exercise.ExerciseConverter;
import com.mescfit.exercise.ExerciseDTO;
import com.mescfit.exercise.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/EC")
@AllArgsConstructor
public class ECController {

    private final ExerciseCategoryService exerciseCategoryService;
    private final ExerciseService exerciseService;
    private final ExerciseConverter converter;

    @GetMapping
    public List<ExerciseDTO> getAll() {
        return exerciseService.getAllExercises();
    }

    @PostMapping("/{id}")
    public ExerciseDTO addEC(@RequestBody List<String> categories, @PathVariable Long id) {
        Exercise exercise = exerciseService.getExercise(id);
        List<String> categoryNames = exerciseCategoryService.addCategoriesToExercise(exercise, categories)
                .stream()
                .map((category) -> category.getId().getCategory().getCategoryName()).toList();
        return converter.entityToDTO(exercise, categoryNames);
    }
}
