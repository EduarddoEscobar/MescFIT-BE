package com.mescfit.categories;

import com.mescfit.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getByCategoryName(String name) {
        return categoryRepository.findByCategoryName(name)
                .orElseThrow(() -> new NotFoundException(String.format("Category with name %s was not found", name)));
    }

    public boolean categoryWithNameDoesNotExists(String name) {
        return !categoryRepository.categoryWithNameExists(name);
    }

    public Category addCategory(Category category) {
        if(categoryRepository.categoryWithNameExists(category.getCategoryName())){
            throw new IllegalStateException("Category with the name " + category.getCategoryName() + " already exists");
        }
        return categoryRepository.save(category);
    }

    public List<Category> addCategories(List<String> categories) {
        List<Category> categoriesAdded = categories.stream()
                .filter(category -> categoryWithNameDoesNotExists(category.toLowerCase()))
                .map(category -> new Category(category.toLowerCase()))
                .collect(Collectors.toList());
        categoriesAdded.forEach(this::addCategory);
        return categoriesAdded;
    }

    public Category removeCategory(String name) {
        Category categoryToDelete = getByCategoryName(name);
        categoryRepository.delete(categoryToDelete);
        return categoryToDelete;
    }
}
