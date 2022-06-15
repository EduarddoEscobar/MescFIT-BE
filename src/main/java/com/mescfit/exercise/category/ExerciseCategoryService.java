package com.mescfit.exercise.category;

import com.mescfit.category.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ExerciseCategoryService {
    private final CategoryService categoryService;

    public List<String> addCategories(String[] categories) {
        List<String> categoriesAdded = Arrays.stream(categories)
                .filter(categoryService::categoryWithNameExists)
                .collect(Collectors.toList());
        
        return categoriesAdded;
    }
}
