package com.mescfit.exerciseCategory;

import com.mescfit.categories.Category;
import com.mescfit.categories.CategoryService;
import com.mescfit.exercise.Exercise;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ExerciseCategoryService {
    private final CategoryService categoryService;
    private final ExerciseCategoryRepository exerciseCategoryRepository;

    public List<ExerciseCategoryKey> getAllCategories() {
        return exerciseCategoryRepository
                .findAll()
                .stream()
                .map(ExerciseCategory::getId)
                .collect(Collectors.toList());
    }

    public List<String> getAllCategoriesForExercise(Long exercise_id) {
        return exerciseCategoryRepository.findAllFromExercise(exercise_id);
    }

    public List<ExerciseCategory> addCategoriesToExercise(Exercise exercise, List<String> categories) {
        categoryService.addCategories(categories);
        return categories.stream()
                .map(categoryService::getByCategoryName)
                .map((category) -> new ExerciseCategoryKey(exercise, category))
                .map((category) -> exerciseCategoryRepository.save(new ExerciseCategory(category)))
                .collect(Collectors.toList());
    }
}
